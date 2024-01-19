package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IInputParameterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class InputParameterController {
    private final IInputParameterService inputParameterService;

    @MutationMapping
    InputParameter persistInputParameter(@Argument InputParameterInput inputParameterInput) {
        return inputParameterService.persistInputParameter(inputParameterInput);
    }
}
