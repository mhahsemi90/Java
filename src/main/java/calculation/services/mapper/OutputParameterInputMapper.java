package calculation.services.mapper;

import calculation.repository.entity.OutputParameter;
import calculation.services.inputs.OutputParameterInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputParameterInputMapper {
    OutputParameter mapToOutputParameter(OutputParameterInput outputParameterInput);
}
