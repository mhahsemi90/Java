package calculation.services.mapper;

import calculation.repository.entity.OutputParameter;
import calculation.services.dto.entity.OutputParameterDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputParameterDtoMapper {
    OutputParameter mapToOutputParameter(
            OutputParameterDto outputParameterDto,
            @Context CycleAvoidingMappingContext context
    );

    OutputParameterDto mapToOutputParameterDto(
            OutputParameter outputParameter,
            @Context CycleAvoidingMappingContext context
    );
}
