package calculation.controller;

import calculation.repository.entity.OutputParameter;
import calculation.services.inputs.OutputParameterInput;
import calculation.services.interfaces.IOutputParameterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OutputParameterController {
    private final IOutputParameterService outputParameterService;

    @MutationMapping
    OutputParameter persistOutputParameter(@Argument OutputParameterInput outputParameterInput) {
        return outputParameterService.persistOutputParameter(outputParameterInput);
    }

    @QueryMapping
    OutputParameter findOutputParameterById(@Argument Long id) {
        return outputParameterService.findOutputParameterById(id);
    }

    @MutationMapping
    void deleterOutputParameterById(@Argument Long id) {
        outputParameterService.deleterOutputParameterById(id);
    }
}
