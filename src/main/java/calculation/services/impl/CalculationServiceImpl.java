package calculation.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.*;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.*;
import calculation.services.dto.CalculatedOutputParameterForElement;
import calculation.services.dto.InputAndOutputParameterElement;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.interfaces.ICalculationService;
import calculation.services.interfaces.IInputParameterService;
import calculation.services.interfaces.IOutputParameterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import calculation.repository.entity.*;
import calculation.repository.service.interfaces.*;

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
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalculationServiceImpl implements ICalculationService {
    private final ICalculationRepository calculationRepository;
    private final IOutputParameterService outputParameterService;
    private final IInputParameterService inputParameterService;
    private final IElementRepository elementRepository;
    private final IInputElementValueRepository inputElementValueRepository;
    private final IInputElementTransactionRepository inputElementTransactionRepository;
    private final IOutputElementValueRepository outputElementValueRepository;
    private final IOutputElementTransactionRepository outputElementTransactionRepository;

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
        Calculation calculation = persistCalculatedTransaction(
                new ArrayList<>()
                , new ArrayList<>()
                , actionDate);
        List<InputAndOutputParameterElement> inputAndOutputParameterElementList = getCalculationInformation(
                inputParameterAndElementValueList,
                outputParameterIdAndFormulaList
        );
        List<InputElementTransaction> inputElementTransactionList = inputElementTransactionRepository.saveAll(
                inputAndOutputParameterElementList
                        .stream()
                        .parallel()
                        .map(o -> createInputElementTransactionEachInputValue(o, calculation))
                        .collect(Collectors.toList())
        );
        inputElementValueRepository.saveAll(
                inputElementTransactionList
                        .stream()
                        .parallel()
                        .map(o -> creatInputParamValue(o, inputAndOutputParameterElementList))
                        .reduce(new ArrayList<>(), (inputElementValues, inputElementValues2) -> {
                            inputElementValues2.addAll(inputElementValues);
                            return inputElementValues2;
                        })
        );
        List<CalculatedOutputParameterForElement> calculateForEachElementByOwnParameter = inputAndOutputParameterElementList
                .stream()
                .parallel()
                .map(this::calculateForEachElementByOwnParameter)
                .collect(Collectors.toList());
        List<OutputElementTransaction> outputElementTransactionList = outputElementTransactionRepository.saveAll(
                calculateForEachElementByOwnParameter
                        .stream()
                        .parallel()
                        .map(o -> createOutputElementTransactionEachInputValue(o, calculation))
                        .collect(Collectors.toList())
        );
        outputElementValueRepository.saveAll(
                outputElementTransactionList
                        .stream()
                        .parallel()
                        .map(o -> creatOutputParamValue(o, calculateForEachElementByOwnParameter))
                        .reduce(new ArrayList<>(), (outputElementValues, outputElementValues1) -> {
                            outputElementValues1.addAll(outputElementValues);
                            return outputElementValues1;
                        })
        );
        return calculationRepository.getReferenceById(calculation.getId());
    }

    private Calculation persistCalculatedTransaction(
            List<InputElementTransaction> inputElementTransactionList
            , List<OutputElementTransaction> outputElementTransactionList
            , Timestamp actionDate) {
        Calculation calculation = new Calculation();
        calculation.setActionDate(actionDate);
        calculation.setInputElementTransactionList(inputElementTransactionList);
        calculation.setOutputElementTransactionList(outputElementTransactionList);
        calculation = calculationRepository.save(calculation);
        return calculation;
    }

    private OutputElementTransaction createOutputElementTransactionEachInputValue(
            CalculatedOutputParameterForElement calculatedOutputParameterForElement
            , Calculation calculation) {
        return new OutputElementTransaction(
                null
                , elementRepository.getReferenceById(calculatedOutputParameterForElement.getElementId())
                , calculation
                , new ArrayList<>()
        );
    }

    private List<OutputElementValue> creatOutputParamValue(
            OutputElementTransaction outputElementTransaction
            , List<CalculatedOutputParameterForElement> calculatedOutputParameterForElements
    ) {
        List<OutputElementValue> result = new ArrayList<>();
        calculatedOutputParameterForElements
                .stream()
                .filter(o -> outputElementTransaction.getElement().getId().equals(o.getElementId()))
                .findFirst()
                .ifPresent(o ->
                        o.getOutputParamValueMapList()
                                .forEach((outputParameter, s) ->
                                        result.add(
                                                new OutputElementValue(
                                                        null
                                                        , s
                                                        , outputParameter.getDataType()
                                                        , outputParameter
                                                        , outputElementTransaction
                                                        , o.getOutputParamFormulaMapList()
                                                        .get(outputParameter.getId())
                                                )
                                        )
                                ));
        return result;
    }

    private InputElementTransaction createInputElementTransactionEachInputValue(
            InputAndOutputParameterElement inputAndOutputParameterElement
            , Calculation calculation
    ) {
        return new InputElementTransaction(
                null,
                elementRepository.getReferenceById(inputAndOutputParameterElement.getElementId()),
                calculation,
                new ArrayList<>());
    }

    private List<InputElementValue> creatInputParamValue(
            InputElementTransaction inputElementTransaction
            , List<InputAndOutputParameterElement> inputAndOutputParameterElementList
    ) {
        List<InputElementValue> result = new ArrayList<>();
        inputAndOutputParameterElementList
                .stream()
                .filter(o -> inputElementTransaction.getElement().getId().equals(o.getElementId()))
                .findFirst()
                .ifPresent(o -> o.getInputParamMapList()
                        .forEach((inputParameter, s) ->
                                result.add(
                                        new InputElementValue(
                                                null,
                                                s,
                                                inputParameter.getDataType(),
                                                inputParameter,
                                                inputElementTransaction
                                        )
                                ))
                );
        return result;
    }

    private CalculatedOutputParameterForElement calculateForEachElementByOwnParameter(InputAndOutputParameterElement inputAndOutputParameterElement) {
        CalculatedOutputParameterForElement calculatedOutputParameterForElement = new CalculatedOutputParameterForElement();
        StringBuilder formulaBuilder = new StringBuilder();
        calculatedOutputParameterForElement.setElementId(inputAndOutputParameterElement.getElementId());
        inputAndOutputParameterElement.getInputParamMapList().forEach(getBuilderToAddInputToFormula(formulaBuilder));
        inputAndOutputParameterElement.getOutputParamMapList().forEach(getBuilderToAddOutputToFormula(formulaBuilder));
        formulaBuilder.append("var _doCalculate = function(){\n").append("var _values = new Map();\n");
        inputAndOutputParameterElement.getOutputParamMapList().keySet().forEach(getBuilderToAddOutputToCalculation(formulaBuilder));
        formulaBuilder.append("return JSON.stringify(Object.fromEntries(_values));\n").append("};\n");
        calculatedOutputParameterForElement.setOutputParamValueMapList(doCalculationFormulaScript(
                formulaBuilder.toString(),
                inputAndOutputParameterElement.getOutputParamMapList()
        ));
        calculatedOutputParameterForElement.setOutputParamFormulaMapList(new LinkedHashMap<>());
        inputAndOutputParameterElement.getOutputParamMapList().forEach((outputParameter, formula) ->
                calculatedOutputParameterForElement.getOutputParamFormulaMapList()
                        .put(outputParameter.getId(), formula)
        );
        return calculatedOutputParameterForElement;
    }

    private Map<OutputParameter, String> doCalculationFormulaScript(String script, Map<OutputParameter, Formula> outputParamMapList) {
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

    private BiConsumer<OutputParameter, Formula> getBuilderToAddOutputToFormula(StringBuilder formulaBuilder) {
        return (outputParameter, formula) ->
                formulaBuilder
                        .append("var getO_")
                        .append(outputParameter.getId())
                        .append(" = function () {\n")
                        .append(formula.getFormula())
                        .append("};\n");
    }

    private Consumer<OutputParameter> getBuilderToAddOutputToCalculation(StringBuilder formulaBuilder) {
        return outputParameter ->
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
