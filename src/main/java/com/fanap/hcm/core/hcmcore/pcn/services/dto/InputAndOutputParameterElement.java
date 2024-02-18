package com.fanap.hcm.core.hcmcore.pcn.services.dto;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Formula;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputAndOutputParameterElement {
    private Long elementId;
    private Map<InputParameter, String> inputParamMapList;
    private Map<OutputParameter, Formula> outputParamMapList;
}
