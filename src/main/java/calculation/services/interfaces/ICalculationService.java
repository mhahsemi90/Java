package calculation.services.interfaces;

import calculation.repository.entity.Calculation;
import calculation.services.inputs.InputParameterAndElementValue;
import calculation.services.inputs.OutputParameterIdAndFormula;

import java.sql.Timestamp;
import java.util.List;

public interface ICalculationService {
    Calculation findCalculationById(Long id);

    Calculation calculate(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            Timestamp actionDate);
}
