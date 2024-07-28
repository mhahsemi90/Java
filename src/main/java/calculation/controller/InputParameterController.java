package calculation.controller;

import calculation.repository.entity.InputParameter;
import calculation.services.inputs.InputParameterInput;
import calculation.services.interfaces.IInputParameterService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class InputParameterController {
    private final IInputParameterService inputParameterService;

    @MutationMapping
    InputParameter persistInputParameter(@Argument InputParameterInput inputParameterInput) {
        return inputParameterService.persistInputParameter(inputParameterInput);
    }

    @QueryMapping
    InputParameter findInputParameterById(@Argument Long id) {
        return inputParameterService.findInputParameterById(id);
    }

    @MutationMapping
    void deleteInputParameterById(@Argument Long id) {
        inputParameterService.deleteInputParameterById(id);
    }
}
