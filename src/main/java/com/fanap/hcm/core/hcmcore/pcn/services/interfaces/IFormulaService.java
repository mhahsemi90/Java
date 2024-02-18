package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Formula;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.FormulaInput;

import java.util.List;

public interface IFormulaService {
    Formula persistFormula(FormulaInput formulaInput);

    Formula findFormulaById(Long id);

    List<Formula> findFormulaByCode(String code);

    Formula findFormulaLastVersion(String code);

    Formula findFormulaByCodeAndVersion(String code, Long version);

    Formula modifyFormulaByCode(String code, String formula);

    void deleteFormulaById(Long id);
}
