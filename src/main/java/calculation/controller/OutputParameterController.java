package calculation.controller;

import calculation.repository.entity.OutputParameter;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.interfaces.OutputParameterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OutputParameterController {
    private final OutputParameterService outputParameterService;

    @MutationMapping
    public OutputParameter persistOutputParameter(@Argument OutputParameterInput outputParameterInput) {
        return outputParameterService.persistOutputParameter(outputParameterInput);
    }

    @QueryMapping
    public OutputParameter findOutputParameterById(@Argument Long id) {
        return outputParameterService.findOutputParameterById(id);
    }

    @MutationMapping
    public void deleterOutputParameterById(@Argument Long id) {
        outputParameterService.deleterOutputParameterById(id);
    }
}
