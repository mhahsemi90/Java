package calculation.controller;

import calculation.repository.entity.ElementType;
import calculation.services.inputs.ElementTypeInput;
import calculation.services.interfaces.IElementTypeService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ElementTypeController {
    private final IElementTypeService elementTypeService;

    @MutationMapping
    public ElementType persistElementType(@Argument ElementTypeInput elementTypeInput) {
        return elementTypeService.persistElementType(elementTypeInput);
    }

    @QueryMapping
    public ElementType findElementTypeById(@Argument Long id) {
        return elementTypeService.findElementTypeById(id);
    }

    @QueryMapping
    public ElementType findElementTypeByCode(@Argument String code) {
        return elementTypeService.findElementTypeByCode(code);
    }

    @MutationMapping
    public void deleteElementTypeById(@Argument Long id) {
        elementTypeService.deleteElementTypeById(id);
    }
}
