package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterAndElementValue;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.ICalculationService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.List;

@Controller
@AllArgsConstructor
public class CalculationController {
    private final ICalculationService calculationService;

    @QueryMapping
    public Calculation findCalculationById(@Argument Long id) {
        return calculationService.findCalculationById(id);
    }

    @MutationMapping
    public Calculation calculate(
            @Argument List<InputParameterAndElementValue> inputParameterAndElementValueList,
            @Argument List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            @Argument Timestamp actionDate) {
        return calculationService.calculate(
                inputParameterAndElementValueList,
                outputParameterIdAndFormulaList,
                actionDate);
    }
}
