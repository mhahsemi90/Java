package calculation.controller;

import calculation.repository.entity.Element;
import calculation.services.inputs.ElementInput;
import calculation.services.interfaces.IElementService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ElementController {
    private final IElementService elementService;

    @MutationMapping
    Element persistElement(@Argument ElementInput elementInput) {
        return elementService.persistElement(elementInput);
    }

    @QueryMapping
    Element findElementById(@Argument Long id) {
        return elementService.findElementById(id);
    }

    @QueryMapping
    Element findElementByVrIdAndByElementType(@Argument String vrId, @Argument String elementTypeCode) {
        return elementService.findElementByVrIdAndByElementType(vrId, elementTypeCode);
    }

    @MutationMapping
    void deleteElementById(@Argument Long id) {
        elementService.deleteElementById(id);
    }
}
