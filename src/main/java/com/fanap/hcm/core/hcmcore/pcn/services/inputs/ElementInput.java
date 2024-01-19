package com.fanap.hcm.core.hcmcore.pcn.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ElementInput {
    private Long id;
    private String vrId;
    private String elementType;
}
