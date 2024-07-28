package calculation.services.interfaces;

import calculation.repository.entity.Formula;
import calculation.services.inputs.FormulaInput;

import java.util.List;

public interface FormulaService {
    Formula persistFormula(FormulaInput formulaInput);

    Formula findFormulaById(Long id);

    List<Formula> findFormulaByCode(String code);

    Formula findFormulaLastVersion(String code);

    Formula findFormulaByCodeAndVersion(String code, Long version);

    Formula modifyFormulaByCode(String code, String formula);

    void deleteFormulaById(Long id);
}
