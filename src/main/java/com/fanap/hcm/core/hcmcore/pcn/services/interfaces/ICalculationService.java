package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Calculation;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterAndElementValue;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterIdAndFormula;

import java.sql.Timestamp;
import java.util.List;

public interface ICalculationService {
    Calculation findCalculationById(Long id);

    Calculation calculate(
            List<InputParameterAndElementValue> inputParameterAndElementValueList,
            List<OutputParameterIdAndFormula> outputParameterIdAndFormulaList,
            Timestamp actionDate);
}
