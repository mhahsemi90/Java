package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class CalculationController {
    private final CalculationService calculationService;

    @QueryMapping
    Calculation findCalculationById(@Argument Long id) {
        Calculation calculation = calculationService.findCalculationById(id);
        return calculation;
    }
}
