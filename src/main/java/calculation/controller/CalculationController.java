package calculation.controller;

import calculation.services.dto.entity.CalculationDto;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.interfaces.CalculationService;
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
    private final CalculationService calculationService;

    @QueryMapping
    public CalculationDto findCalculationById(@Argument Long id) {
        return calculationService.findCalculationById(id);
    }

    @MutationMapping
    public CalculationDto calculate(
            @Argument List<InputParameterAndElementValue> inputParameterAndElementValueList,
            @Argument List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            @Argument String actionDate) {
        return calculationService.calculate(
                inputParameterAndElementValueList,
                outputParameterIdAndFormulaList,
                new Timestamp(Long.parseLong(actionDate)));
    }
}
