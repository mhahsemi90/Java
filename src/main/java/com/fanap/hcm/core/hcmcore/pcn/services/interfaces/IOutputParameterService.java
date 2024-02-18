package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Formula;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface IOutputParameterService {
    OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput);

    OutputParameter findOutputParameterById(Long id);

    Collector<OutputParameterIdAndFormula, ?, Map<OutputParameter, Formula>> collectOutputInformationToMap();

    void deleterOutputParameterById(Long id);
}
