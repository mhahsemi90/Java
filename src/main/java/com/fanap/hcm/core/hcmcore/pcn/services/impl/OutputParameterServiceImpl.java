package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Formula;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IFormulaRepository;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IOutputParameterRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import com.fanap.hcm.core.hcmcore.pcn.services.mapper.OutputParameterInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OutputParameterServiceImpl implements IOutputParameterService {
    private final IFormulaRepository formulaRepository;
    private final IOutputParameterRepository outputParameterRepository;
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
