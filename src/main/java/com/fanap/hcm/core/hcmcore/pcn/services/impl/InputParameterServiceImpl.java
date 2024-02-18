package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IInputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterAndElementValue;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IInputParameterService;
import com.fanap.hcm.core.hcmcore.pcn.services.mapper.InputParameterInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InputParameterServiceImpl implements IInputParameterService {
    private final IInputParameterRepository inputParameterRepository;
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
