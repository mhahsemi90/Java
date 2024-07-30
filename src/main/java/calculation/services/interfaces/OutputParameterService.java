package calculation.services.interfaces;

import calculation.repository.entity.OutputParameter;
import calculation.services.dto.entity.FormulaDto;
import calculation.services.dto.entity.OutputParameterDto;
import calculation.services.inputs.OutputParameterIdAndFormula;
import calculation.services.inputs.OutputParameterInput;

import java.util.Map;
import java.util.stream.Collector;

public interface OutputParameterService {
    OutputParameter persistOutputParameter(OutputParameterInput outputParameterInput);

    OutputParameter findOutputParameterById(Long id);

    Collector<OutputParameterIdAndFormula, ?, Map<OutputParameterDto, FormulaDto>> collectOutputInformationToMap();

    void deleterOutputParameterById(Long id);
}
