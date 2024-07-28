package calculation.services.impl;

import calculation.repository.entity.InputParameter;
import calculation.repository.service.interfaces.InputParameterRepository;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.InputParameterInput;
import calculation.services.interfaces.InputParameterService;
import calculation.services.mapper.InputParameterInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InputParameterServiceImpl implements InputParameterService {
    private final InputParameterRepository inputParameterRepository;
    private final InputParameterInputMapper inputParameterInputMapper;

    @Override
    public InputParameter persistInputParameter(InputParameterInput inputParameterInput) {
        return inputParameterRepository.save(
                inputParameterInputMapper.mapToInputParameter(inputParameterInput)
        );
    }

    @Override
    public InputParameter findInputParameterById(Long id) {
        return inputParameterRepository.findById(id).orElse(null);
    }

    @Override
    public Collector<InputParameterAndElementValue, ?, Map<InputParameter, String>> collectInputInformationToMap() {
        return Collectors.toMap(
                inputParameterAndElementValue ->
                        inputParameterRepository.getReferenceById(inputParameterAndElementValue.getInputParameterId())
                , InputParameterAndElementValue::getValue
        );
    }

    @Override
    public void deleteInputParameterById(Long id) {
        inputParameterRepository.deleteById(id);
    }
}
