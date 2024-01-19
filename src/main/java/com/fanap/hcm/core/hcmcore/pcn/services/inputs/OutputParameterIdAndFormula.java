package com.fanap.hcm.core.hcmcore.pcn.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputParameterIdAndFormula {
    private Long outputParameterId;
    private Long elementId;
    private String formula;
}
