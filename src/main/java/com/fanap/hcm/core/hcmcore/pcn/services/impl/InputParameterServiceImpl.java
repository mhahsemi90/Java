package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IInputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.InputParameterService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class InputParameterServiceImpl implements InputParameterService {
    private final IInputParameterRepository IInputParameterRepository;
    private final ModelMapper modelMapper;

    public InputParameterServiceImpl(IInputParameterRepository IInputParameterRepository, ModelMapper modelMapper) {
        this.IInputParameterRepository = IInputParameterRepository;
        this.modelMapper = modelMapper;
        TypeMap<InputParameterInput, InputParameter> inputParameterDtoInputParameterTypeMap = modelMapper
                .createTypeMap(InputParameterInput.class, InputParameter.class);
        inputParameterDtoInputParameterTypeMap.addMappings(mapper -> mapper.skip(InputParameter::setId));
    }

    @Override
    public InputParameter persistNewInputParameter(InputParameterInput inputParameterInput) {
        return IInputParameterRepository.save(
                modelMapper.map(inputParameterInput, InputParameter.class)
        );
    }
}
