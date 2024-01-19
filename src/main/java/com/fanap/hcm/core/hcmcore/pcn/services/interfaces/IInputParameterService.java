package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;

public interface IInputParameterService {
    InputParameter persistInputParameter(InputParameterInput inputParameterInput);
}
