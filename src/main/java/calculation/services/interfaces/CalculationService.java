package calculation.services.interfaces;

import calculation.repository.entity.Calculation;
import calculation.services.dto.entity.CalculationDto;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.OutputParameterIdAndFormula;

import java.sql.Timestamp;
import java.util.List;

public interface CalculationService {
    CalculationDto findCalculationById(Long id);

    CalculationDto calculate(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            Timestamp actionDate);
}
