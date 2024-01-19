package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IOutputParameterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OutputParameterController {
    private final IOutputParameterService outputParameterService;

    @MutationMapping
    OutputParameter persistOutputParameter(@Argument OutputParameterInput outputParameterInput) {
        return outputParameterService.persistOutputParameter(outputParameterInput);
    }
}
