package calculation.services.interfaces;

import calculation.repository.entity.Formula;
import calculation.repository.entity.OutputParameter;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.inputs.OutputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface OutputParameterService {
    OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput);

    OutputParameter findOutputParameterById(Long id);

    Collector<OutputParameterIdAndFormula, ?, Map<OutputParameter, Formula>> collectOutputInformationToMap();

    void deleterOutputParameterById(Long id);
}
