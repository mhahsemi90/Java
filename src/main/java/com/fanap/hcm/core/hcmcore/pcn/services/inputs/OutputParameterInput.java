package com.fanap.hcm.core.hcmcore.pcn.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputParameterInput {
    private Long id;
    private String code;
    private String title;
    private String dataType;
}
