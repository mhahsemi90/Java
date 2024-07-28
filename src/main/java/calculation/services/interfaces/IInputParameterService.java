package calculation.services.interfaces;

import calculation.repository.entity.InputParameter;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.InputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface IInputParameterService {
    InputParameter persistInputParameter(InputParameterInput inputParameterInput);

    InputParameter findInputParameterById(Long id);

    Collector<InputParameterAndElementValue, ?, Map<InputParameter, String>> collectInputInformationToMap();

    void deleteInputParameterById(Long id);
}
