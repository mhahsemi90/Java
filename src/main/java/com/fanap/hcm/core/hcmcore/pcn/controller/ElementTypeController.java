package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementTypeInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementTypeService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ElementTypeController {
    private final IElementTypeService elementTypeService;

    @MutationMapping
    public ElementType persistElementType(@Argument ElementTypeInput elementTypeInput) {
        return elementTypeService.persistElementType(elementTypeInput);
    }
}
