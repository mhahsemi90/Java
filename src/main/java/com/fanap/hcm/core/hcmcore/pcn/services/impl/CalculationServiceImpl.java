package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.*;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.*;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.CalculatedOutputParameterForElement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.InputAndOutputParameterElement;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterAndElementValue;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.ICalculationService;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IInputParameterService;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CalculationServiceImpl implements ICalculationService {
    private final ICalculationRepository calculationRepository;
    private final IOutputParameterService outputParameterService;
    private final IInputParameterService inputParameterService;
    private final IElementRepository elementRepository;
    private final IInputElementTransactionRepository inputElementTransactionRepository;
    private final IOutputElementTransactionRepository outputElementTransactionRepository;
    private final IInputElementValueRepository inputElementValueRepository;
    private final IOutputElementValueRepository outputElementValueRepository;

    @Override
    public Calculation findCalculationById(Long id) {
        return calculationRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Calculation calculate(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            Timestamp actionDate) {
        List<InputAndOutputParameterElement> inputAndOutputParameterElementList = getCalculationInformation(
                inputParameterAndElementValueList,
                outputParameterIdAndFormulaList
        );
        List<InputElementTransaction> inputElementTransactionList = inputAndOutputParameterElementList
                .stream()
                .sequential()
                .map(this::persistEachInputValue)
                .collect(Collectors.toList());
        Stream<CalculatedOutputParameterForElement> calculateForEachElementByOwnParameter = inputAndOutputParameterElementList
                .stream()
                .parallel()
                .map(this::calculateForEachElementByOwnParameter);
        List<OutputElementTransaction> outputElementTransactionList = calculateForEachElementByOwnParameter
                .sequential()
                .map(this::persistEachOutputCalculatedValue)
                .collect(Collectors.toList());
        return persistCalculatedTransaction(
                inputElementTransactionList
                , outputElementTransactionList
                , actionDate);
    }

    private Calculation persistCalculatedTransaction(
            List<InputElementTransaction> inputElementTransactionList
            , List<OutputElementTransaction> outputElementTransactionList
            , Timestamp actionDate) {
        Calculation calculation = new Calculation();
        calculation.setActionDate(actionDate);
        calculation = calculationRepository.save(calculation);
        for (InputElementTransaction o : inputElementTransactionList) {
            o.setCalculation(calculation);
            inputElementTransactionRepository.save(o);
        }
        for (OutputElementTransaction o : outputElementTransactionList) {
            o.setCalculation(calculation);
            outputElementTransactionRepository.save(o);
        }
        return calculation;
    }

    private OutputElementTransaction persistEachOutputCalculatedValue(CalculatedOutputParameterForElement calculatedOutputParameterForElement) {
        OutputElementTransaction outputElementTransaction = new OutputElementTransaction();
        Element element = elementRepository.getReferenceById(calculatedOutputParameterForElement.getElementId());
        outputElementTransaction.setElement(element);
        outputElementTransaction = outputElementTransactionRepository.save(outputElementTransaction);
        calculatedOutputParameterForElement.getOutputParamMapList()
                .forEach(getBuilderForCreatOutputParamValue(outputElementTransaction));
        return outputElementTransaction;
    }

    private BiConsumer<OutputParameter, String> getBuilderForCreatOutputParamValue(OutputElementTransaction outputElementTransaction) {
        return (outputParameter, s) ->
                outputElementValueRepository.save(new OutputElementValue(
                        null,
                        s,
                        outputParameter.getDataType(),
                        outputParameter,
                        outputElementTransaction
                ));
    }

    private InputElementTransaction persistEachInputValue(InputAndOutputParameterElement inputAndOutputParameterElement) {
        InputElementTransaction inputElementTransaction = new InputElementTransaction();
        Element element = elementRepository.getReferenceById(inputAndOutputParameterElement.getElementId());
        inputElementTransaction.setElement(element);
        inputElementTransaction = inputElementTransactionRepository.save(inputElementTransaction);
        inputAndOutputParameterElement.getInputParamMapList()
                .forEach(getBuilderForCreatInputParamValue(inputElementTransaction));
        return inputElementTransaction;
    }

    private BiConsumer<InputParameter, String> getBuilderForCreatInputParamValue(InputElementTransaction inputElementTransaction) {
        return (inputParameter, s) ->
                inputElementValueRepository.save(new InputElementValue(
                        null,
                        s,
                        inputParameter.getDataType(),
                        inputParameter,
                        inputElementTransaction
                ));
    }

    private CalculatedOutputParameterForElement calculateForEachElementByOwnParameter(InputAndOutputParameterElement inputAndOutputParameterElement) {
        CalculatedOutputParameterForElement calculatedOutputParameterForElement = new CalculatedOutputParameterForElement();
        StringBuilder formulaBuilder = new StringBuilder();
        calculatedOutputParameterForElement.setElementId(inputAndOutputParameterElement.getElementId());
        inputAndOutputParameterElement.getInputParamMapList().forEach(getBuilderToAddInputToFormula(formulaBuilder));
        inputAndOutputParameterElement.getOutputParamMapList().forEach(getBuilderToAddOutputToFormula(formulaBuilder));
        formulaBuilder.append("var _doCalculate = function(){\n").append("var _values = new Map();\n");
        inputAndOutputParameterElement.getOutputParamMapList().forEach(getBuilderToAddOutputToCalculation(formulaBuilder));
        formulaBuilder.append("return JSON.stringify(Object.fromEntries(_values));\n").append("};\n");
        calculatedOutputParameterForElement.setOutputParamMapList(doCalculationFormulaScript(
                formulaBuilder.toString(),
                inputAndOutputParameterElement.getOutputParamMapList()
        ));
        return calculatedOutputParameterForElement;
    }

    private Map<OutputParameter, String> doCalculationFormulaScript(String script, Map<OutputParameter, String> outputParamMapList) {
        Map<Object, Object> outputParamMap = new LinkedHashMap<>();
        Map<OutputParameter, String> outputParameterValueMap = new LinkedHashMap<>();
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("graal.js");
            Reader inputString = new StringReader(script);
            BufferedReader reader = new BufferedReader(inputString);
            engine.eval(reader);
            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("_doCalculate");
            ObjectMapper objectMapper = new ObjectMapper();
            outputParamMap.putAll(new LinkedHashMap<>((Map<?, ?>) objectMapper.readValue(String.valueOf(result), Object.class)));
        } catch (ScriptException | NoSuchMethodException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        outputParamMapList.forEach((outputParameter, s) -> {
            String result = String.valueOf(outputParamMap.get("O_" + outputParameter.getId()));
            outputParameterValueMap.put(outputParameter, result);
        });
        return outputParameterValueMap;
    }

    private BiConsumer<InputParameter, String> getBuilderToAddInputToFormula(StringBuilder formulaBuilder) {
        return (inputParameter, s) ->
                formulaBuilder
                        .append("var I_")
                        .append(inputParameter.getId())
                        .append(" = ")
                        .append(setInputValue(inputParameter, s))
                        .append(";\n");
    }

    private BiConsumer<OutputParameter, String> getBuilderToAddOutputToFormula(StringBuilder formulaBuilder) {
        return (outputParameter, s) ->
                formulaBuilder
                        .append("var getO_")
                        .append(outputParameter.getId())
                        .append(" = function () {\n")
                        .append(s)
                        .append("};\n");
    }

    private BiConsumer<OutputParameter, String> getBuilderToAddOutputToCalculation(StringBuilder formulaBuilder) {
        return (outputParameter, s) ->
                formulaBuilder
                        .append("_values.set(\"O_")
                        .append(outputParameter.getId())
                        .append("\",getO_")
                        .append(outputParameter.getId())
                        .append("());\n");
    }

    private Object setInputValue(InputParameter inputParameter, String s) {
        return inputParameter.getDataType().equals("TEXT") ? "'" + s + "'" : s;
    }

    private List<InputAndOutputParameterElement> getCalculationInformation(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList) {
        Set<Long> allElementId = inputParameterAndElementValueList
                .stream()
                .map(InputParameterAndElementValue::getElementId)
                .collect(Collectors.toSet());
        List<InputAndOutputParameterElement> inputAndOutputParameterElementList = new ArrayList<>();
        allElementId.forEach(aLong -> {
            InputAndOutputParameterElement inputAndOutputParameterElement = new InputAndOutputParameterElement();
            inputAndOutputParameterElement.setElementId(aLong);
            inputAndOutputParameterElement.setInputParamMapList(
                    inputParameterAndElementValueList
                            .stream()
                            .filter(getFilterForAllSameInput(aLong))
                            .collect(inputParameterService.collectInputInformationToMap())
            );
            inputAndOutputParameterElement.setOutputParamMapList(
                    outputParameterIdAndFormulaList
                            .stream()
                            .filter(getFilterForAllSameOutput(aLong))
                            .collect(outputParameterService.collectOutputInformationToMap())
            );
            inputAndOutputParameterElementList.add(inputAndOutputParameterElement);
        });
        return inputAndOutputParameterElementList;
    }

    private Predicate<InputParameterAndElementValue> getFilterForAllSameInput(Long elementId) {
        return inputParameterAndElementValue ->
                inputParameterAndElementValue.getElementId().equals(elementId);
    }

    private Predicate<OutputParameterIdAndFormula> getFilterForAllSameOutput(Long elementId) {
        return inputParameterAndElementValue ->
                inputParameterAndElementValue.getElementId().equals(elementId);
    }
}
