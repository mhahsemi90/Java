package calculation.services.mapper;

import calculation.repository.entity.OutputElementValue;
import calculation.services.dto.entity.OutputElementValueDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputElementValueDtoMapper {
    OutputElementValue mapToOutputElementValue(OutputElementValueDto outputElementValueDto);
}
