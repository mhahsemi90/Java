package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IOutputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.OutputParameterService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class OutputParameterServiceImpl implements OutputParameterService {
    private final IOutputParameterRepository IOutputParameterRepository;
    private final ModelMapper modelMapper;

    public OutputParameterServiceImpl(IOutputParameterRepository IOutputParameterRepository, ModelMapper modelMapper) {
        this.IOutputParameterRepository = IOutputParameterRepository;
        this.modelMapper = modelMapper;
        TypeMap<OutputParameterInput, OutputParameter> outputParameterDtoOutputParameterTypeMap = modelMapper
                .createTypeMap(OutputParameterInput.class, OutputParameter.class);
        outputParameterDtoOutputParameterTypeMap.addMappings(mapper -> mapper.skip(OutputParameter::setId));
    }

    @Override
    public OutputParameter persistNewOutputParameter(OutputParameterInput outputParameterInput) {
        return IOutputParameterRepository.save(
                modelMapper.map(outputParameterInput, OutputParameter.class)
        );
    }
}
