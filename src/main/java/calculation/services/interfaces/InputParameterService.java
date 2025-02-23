package calculation.services.interfaces;

import calculation.repository.entity.InputParameter;
import calculation.services.dto.entity.InputParameterDto;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.InputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface InputParameterService {
    InputParameter persistInputParameter(InputParameterInput inputParameterInput);

    InputParameter findInputParameterById(Long id);

    Collector<InputParameterAndElementValue, ?, Map<InputParameterDto, String>> collectInputInformationToMap();

    void deleteInputParameterById(Long id);
}
