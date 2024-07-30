package calculation.services.mapper;

import calculation.repository.entity.InputElementValue;
import calculation.services.dto.entity.InputElementValueDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputElementValueDtoMapper {
    InputElementValue mapToInputElementValue(
            InputElementValueDto inputElementValueDto,
            @Context CycleAvoidingMappingContext context
    );
}
