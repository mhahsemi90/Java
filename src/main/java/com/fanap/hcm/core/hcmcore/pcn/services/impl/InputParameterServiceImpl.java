package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IInputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IInputParameterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InputParameterServiceImpl implements IInputParameterService {
    private final IInputParameterRepository inputParameterRepository;
    private final ModelMapper modelMapper;

    @Override
    public InputParameter persistInputParameter(InputParameterInput inputParameterInput) {
        return inputParameterRepository.save(
                modelMapper.map(inputParameterInput, InputParameter.class)
        );
    }
}
