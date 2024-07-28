package calculation.services.mapper;

import calculation.repository.entity.ElementType;
import calculation.services.inputs.ElementTypeInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementTypeInputMapper {
    ElementType mapToElementType(ElementTypeInput elementTypeInput);
}
