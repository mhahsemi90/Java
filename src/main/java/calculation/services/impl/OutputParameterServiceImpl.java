package calculation.services.impl;

import calculation.repository.entity.Formula;
import calculation.repository.entity.OutputParameter;
import calculation.repository.service.interfaces.FormulaRepository;
import calculation.repository.service.interfaces.OutputParameterRepository;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.interfaces.OutputParameterService;
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

    @Override
    public OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput) {
        return outputParameterRepository.save(
                outputParameterInputMapper.mapToOutputParameter(outputParameterInput)
        );
    }

    @Override
    public OutputParameter findOutputParameterById(Long id) {
        return outputParameterRepository.findById(id).orElse(null);
    }

    @Override
    public Collector<OutputParameterIdAndFormula, ?, Map<OutputParameter, Formula>> collectOutputInformationToMap() {
        return Collectors.toMap(
                outputParameterIdAndFormula ->
                        outputParameterRepository.getReferenceById(outputParameterIdAndFormula.getOutputParameterId())
                , outputParameterIdAndFormula ->
                        formulaRepository.findById(outputParameterIdAndFormula.getFormulaId()).orElse(null)
        );
    }

    @Override
    public void deleterOutputParameterById(Long id) {
        outputParameterRepository.deleteById(id);
    }
}
