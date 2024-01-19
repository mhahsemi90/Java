package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ElementController {
    private final IElementService elementService;

    @QueryMapping
    Element findElementById(@Argument Long id) {
        return elementService.findElementById(id);
    }

    @QueryMapping
    Element findElementByVrIdAndByElementType(@Argument String vrId, @Argument String elementType) {
        return elementService.findElementByVrIdAndByElementType(vrId, elementType);
    }

    @MutationMapping
    Element persistElement(@Argument ElementInput elementInput) {
        return elementService.persistElement(elementInput);
    }
}
