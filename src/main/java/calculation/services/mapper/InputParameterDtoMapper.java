package calculation.services.mapper;

import calculation.repository.entity.InputParameter;
import calculation.services.dto.entity.InputParameterDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputParameterDtoMapper {
    InputParameter mapToInputParameter(InputParameterDto inputParameterDto);

    InputParameterDto mapToInputParameterDto(
            InputParameter inputParameter,
            @Context CycleAvoidingMappingContext context
    );
}
