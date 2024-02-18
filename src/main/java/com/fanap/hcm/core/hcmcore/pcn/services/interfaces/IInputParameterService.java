package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterAndElementValue;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface IInputParameterService {
    InputParameter persistInputParameter(InputParameterInput inputParameterInput);

    InputParameter findInputParameterById(Long id);

    Collector<InputParameterAndElementValue, ?, Map<InputParameter, String>> collectInputInformationToMap();

    void deleteInputParameterById(Long id);
}
