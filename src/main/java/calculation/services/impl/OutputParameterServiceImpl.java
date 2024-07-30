package calculation.services.impl;

import calculation.repository.entity.OutputParameter;
import calculation.repository.service.interfaces.FormulaRepository;
import calculation.repository.service.interfaces.OutputParameterRepository;
import calculation.services.dto.entity.FormulaDto;
import calculation.services.dto.entity.OutputParameterDto;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.interfaces.OutputParameterService;
import calculation.services.mapper.CycleAvoidingMappingContext;
import calculation.services.mapper.FormulaDtoMapper;
import calculation.services.mapper.OutputParameterDtoMapper;
import calculation.services.mapper.OutputParameterInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OutputParameterServiceImpl implements OutputParameterService {
    private final FormulaRepository formulaRepository;
    private final OutputParameterRepository outputParameterRepository;
    private final OutputParameterInputMapper outputParameterInputMapper;
    private final OutputParameterDtoMapper outputParameterDtoMapper;
    private final FormulaDtoMapper formulaDtoMapper;

    @Override
    public OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput) {
        return outputParameterRepository.save(
                outputParameterInputMapper
                        .mapToOutputParameter(outputParameterInput)
        );
    }

    @Override
    public OutputParameter findOutputParameterById(Long id) {
        return outputParameterRepository.findById(id).orElse(null);
    }

    @Override
    public Collector<OutputParameterIdAndFormula, ?, Map<OutputParameterDto, FormulaDto>> collectOutputInformationToMap() {
        return Collectors.toMap(
                outputParameterIdAndFormula ->
                        outputParameterDtoMapper
                                .mapToOutputParameterDto(
                                        outputParameterRepository
                                                .getReferenceById(
                                                        outputParameterIdAndFormula.getOutputParameterId()
                                                ),
                                        new CycleAvoidingMappingContext()
                                )
                , outputParameterIdAndFormula ->
                        formulaDtoMapper
                                .mapToFormulaDto(
                                        formulaRepository
                                                .findById(
                                                        outputParameterIdAndFormula.getFormulaId()
                                                ).orElse(null),
                                        new CycleAvoidingMappingContext()
                                )
        );
    }

    @Override
    public void deleterOutputParameterById(Long id) {
        outputParameterRepository.deleteById(id);
    }
}
