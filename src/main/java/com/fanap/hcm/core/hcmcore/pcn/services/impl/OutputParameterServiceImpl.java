package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IOutputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OutputParameterServiceImpl implements IOutputParameterService {
    private final IOutputParameterRepository outputParameterRepository;
    private final ModelMapper modelMapper;

    @Override
    public OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput) {
        return outputParameterRepository.save(
                modelMapper.map(outputParameterInput, OutputParameter.class)
        );
    }
}
