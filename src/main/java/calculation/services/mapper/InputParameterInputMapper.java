package calculation.services.mapper;

import calculation.repository.entity.InputParameter;
import calculation.services.inputs.InputParameterInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputParameterInputMapper {
    InputParameter mapToInputParameter(InputParameterInput inputParameterInput);
}
