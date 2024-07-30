package calculation.services.mapper;

import calculation.repository.entity.ElementType;
import calculation.services.dto.entity.ElementTypeDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementTypeDtoMapper {
    ElementType mapToElementType(
            ElementTypeDto elementTypeDto,
            @Context CycleAvoidingMappingContext context
    );
}
