package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;

public interface IOutputParameterService {
    OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput);
}
