package calculation.services.mapper;

import calculation.repository.entity.Element;
import calculation.services.dto.entity.ElementDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementDtoMapper {
    Element mapToElement(
            ElementDto elementDto,
            @Context CycleAvoidingMappingContext context
    );
}
