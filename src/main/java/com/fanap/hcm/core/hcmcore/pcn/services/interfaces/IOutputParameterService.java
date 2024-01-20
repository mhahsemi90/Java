package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface IOutputParameterService {
    OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput);

    Collector<OutputParameterIdAndFormula, ?, Map<OutputParameter, String>> collectOutputInformationToMap();
}
