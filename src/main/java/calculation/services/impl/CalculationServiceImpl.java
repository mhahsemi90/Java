package calculation.services.impl;

import calculation.repository.service.interfaces.CalculationRepository;
import calculation.repository.service.interfaces.ElementRepository;
import calculation.services.dto.CalculatedOutputParameterForElement;
import calculation.services.dto.InputAndOutputParameterElement;
import calculation.services.dto.entity.*;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.interfaces.CalculationService;
import calculation.services.interfaces.ElementService;
import calculation.services.interfaces.InputParameterService;
import calculation.services.interfaces.OutputParameterService;
import calculation.services.mapper.CalculationDtoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
public class CalculationServiceImpl implements CalculationService {
    private final CalculationRepository calculationRepository;
    private final OutputParameterService outputParameterService;
    private final InputParameterService inputParameterService;
    private final ElementRepository elementRepository;
    private final CalculationDtoMapper calculationDtoMapper;

    @Override
    public CalculationDto findCalculationById(Long id) {
        return calculationDtoMapper
                .mapToCalculationDto(
                        calculationRepository.findById(id).orElse(null)
                );
    }

    @Override
    public CalculationDto calculate(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            Timestamp actionDate) {
        List<InputAndOutputParameterElement> inputAndOutputParameterElementList = getCalculationInformation(
                inputParameterAndElementValueList,
                outputParameterIdAndFormulaList
        );
        CalculationDto calculationDto = new CalculationDto(
                null,
                actionDate,
                new ArrayList<>(),
                new ArrayList<>()
        );
        List<InputElementTransactionDto> inputElementTransactionDtoList =
                inputAndOutputParameterElementList
                        .stream()
                        .parallel()
                        .map(o -> createInputElementTransactionEachInputValue(o, calculationDto))
                        .toList();
        List<CalculatedOutputParameterForElement> calculateForEachElementByOwnParameter =
                inputAndOutputParameterElementList
                        .stream()
                        .parallel()
                        .map(this::calculateForEachElementByOwnParameter)
                        .toList();
        List<OutputElementTransactionDto> outputElementTransactionDtoList = new ArrayList<>();
                /*
                calculateForEachElementByOwnParameter
                        .stream()
                        .map(o -> createOutputElementTransactionEachInputValue(o, calculation))
                        .toList();*/
        calculationDto.setInputElementTransactionList(inputElementTransactionDtoList);
        calculationDto.setOutputElementTransactionList(outputElementTransactionDtoList);
        return calculationDtoMapper
                .mapToCalculationDto(
                        calculationRepository.save(
                                calculationDtoMapper
                                        .mapToCalculation(
                                                calculationDto
                                        )
                        )
                );
    }

    private OutputElementTransactionDto createOutputElementTransactionEachInputValue(
            CalculatedOutputParameterForElement calculatedOutputParameterForElement
            , CalculationDto calculationDto) {
        OutputElementTransactionDto outputElementTransactionDto = new OutputElementTransactionDto(
                null
                , elementRepository.getReferenceById(calculatedOutputParameterForElement.getElementId())
                , calculationDto
                , new ArrayList<>()
        );
        outputElementTransactionDto.setOutputElementValueList(
                creatOutputParamValue(
                        outputElementTransactionDto,
                        calculatedOutputParameterForElement
                )
        );
        return outputElementTransactionDto;
    }

    private List<OutputElementValueDto> creatOutputParamValue(
            OutputElementTransactionDto outputElementTransactionDto
            , CalculatedOutputParameterForElement calculatedOutputParameterForElements
    ) {
        List<OutputElementValueDto> result = new ArrayList<>();
        calculatedOutputParameterForElements.getOutputParamValueMapList()
                .forEach((outputParameterDto, s) ->
                        result.add(
                                new OutputElementValueDto(
                                        null
                                        , s
                                        , outputParameterDto.getDataType()
                                        , outputParameterDto
                                        , outputElementTransactionDto
                                        , calculatedOutputParameterForElements.getOutputParamFormulaMapList()
                                        .get(outputParameterDto.getId())
                                )
                        )
                );
        return result;
    }


    private InputElementTransactionDto createInputElementTransactionEachInputValue(
            InputAndOutputParameterElement inputAndOutputParameterElement
            , CalculationDto calculationDto
    ) {
        InputElementTransactionDto inputElementTransactionDto = new InputElementTransactionDto();
        try {

            inputElementTransactionDto = new InputElementTransactionDto(
                    null,
                    elementRepository.getReferenceById(inputAndOutputParameterElement.getElementId()),
                    calculationDto,
                    new ArrayList<>());
            inputElementTransactionDto.setInputElementValueList(
                    creatInputParamValue(
                            inputElementTransactionDto,
                            inputAndOutputParameterElement
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return inputElementTransactionDto;
    }

    private List<InputElementValueDto> creatInputParamValue(
            InputElementTransactionDto inputElementTransaction
            , InputAndOutputParameterElement inputAndOutputParameterElement
    ) {
        List<InputElementValueDto> result = new ArrayList<>();
        try {
            inputAndOutputParameterElement
                    .getInputParamMapList()
                    .forEach((inputParameter, s) ->
                            result.add(
                                    new InputElementValueDto(
                                            null,
                                            s,
                                            inputParameter.getDataType(),
                                            inputParameter,
                                            inputElementTransaction
                                    )
                            )
                    );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private CalculatedOutputParameterForElement calculateForEachElementByOwnParameter(InputAndOutputParameterElement inputAndOutputParameterElement) {
        CalculatedOutputParameterForElement calculatedOutputParameterForElement = new CalculatedOutputParameterForElement();
        StringBuilder formulaBuilder = new StringBuilder();
        calculatedOutputParameterForElement
                .setElementId(inputAndOutputParameterElement.getElementId());
        inputAndOutputParameterElement
                .getInputParamMapList()
                .forEach(getBuilderToAddInputToFormula(formulaBuilder));
        inputAndOutputParameterElement
                .getOutputParamMapList()
                .forEach(getBuilderToAddOutputToFormula(formulaBuilder));
        formulaBuilder
                .append("var _doCalculate = function(){\n")
                .append("var _values = new Map();\n");
        inputAndOutputParameterElement
                .getOutputParamMapList()
                .keySet()
                .forEach(getBuilderToAddOutputToCalculation(formulaBuilder));
        formulaBuilder
                .append("return JSON.stringify(Object.fromEntries(_values));\n")
                .append("};\n");
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

    private Map<OutputParameterDto, String> doCalculationFormulaScript(
            String script,
            Map<OutputParameterDto, FormulaDto> outputParameterDtoFormulaDtoMap
    ) {
        Map<Object, Object> outputParamMap = new LinkedHashMap<>();
        Map<OutputParameterDto, String> outputParameterDtoValueMap = new LinkedHashMap<>();
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("graal.js");
            Reader inputString = new StringReader(script);
            BufferedReader reader = new BufferedReader(inputString);
            engine.eval(reader);
            Invocable invocable = (Invocable) engine;
            Object result = invocable.invokeFunction("_doCalculate");
            ObjectMapper objectMapper = new ObjectMapper();
            outputParamMap.putAll(
                    new LinkedHashMap<>(
                            (Map<?, ?>) objectMapper.readValue(String.valueOf(result), Object.class)
                    )
            );
        } catch (ScriptException | NoSuchMethodException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        outputParameterDtoFormulaDtoMap.forEach((outputParameter, s) -> {
            String result = String.valueOf(outputParamMap.get("O_" + outputParameter.getId()));
            outputParameterDtoValueMap.put(outputParameter, result);
        });
        return outputParameterDtoValueMap;
    }

    private BiConsumer<InputParameterDto, String> getBuilderToAddInputToFormula(StringBuilder formulaBuilder) {
        return (inputParameterDto, s) ->
                formulaBuilder
                        .append("var I_")
                        .append(inputParameterDto.getId())
                        .append(" = ")
                        .append(setInputValue(inputParameterDto, s))
                        .append(";\n");
    }

    private BiConsumer<OutputParameterDto, FormulaDto> getBuilderToAddOutputToFormula(StringBuilder formulaBuilder) {
        return (outputParameter, formula) ->
                formulaBuilder
                        .append("var getO_")
                        .append(outputParameter.getId())
                        .append(" = function () {\n")
                        .append(formula.getFormula())
                        .append("};\n");
    }

    private Consumer<OutputParameterDto> getBuilderToAddOutputToCalculation(StringBuilder formulaBuilder) {
        return outputParameterDto ->
                formulaBuilder
                        .append("_values.set(\"O_")
                        .append(outputParameterDto.getId())
                        .append("\",getO_")
                        .append(outputParameterDto.getId())
                        .append("());\n");
    }

    private Object setInputValue(InputParameterDto inputParameterDto, String s) {
        return inputParameterDto.getDataType().equals("TEXT") ? "'" + s + "'" : s;
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
