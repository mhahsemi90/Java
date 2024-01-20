package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IOutputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    @Override
    public Collector<OutputParameterIdAndFormula, ?, Map<OutputParameter, String>> collectOutputInformationToMap() {
        return Collectors.toMap(
                outputParameterIdAndFormula ->
                        outputParameterRepository.getReferenceById(outputParameterIdAndFormula.getOutputParameterId())
                , OutputParameterIdAndFormula::getFormula
        );
    }
}
