package calculation.services.mapper;

import calculation.services.dto.entity.ElementTypeDto;
import calculation.services.inputs.ElementTypeInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementTypeInputMapper {
    ElementTypeDto mapToElementTypeDto(ElementTypeInput elementTypeInput);
}
