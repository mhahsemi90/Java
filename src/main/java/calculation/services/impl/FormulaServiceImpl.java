package calculation.services.impl;

import calculation.repository.entity.Formula;
import calculation.repository.service.interfaces.FormulaRepository;
import calculation.services.inputs.FormulaInput;
import calculation.services.interfaces.FormulaService;
import calculation.services.mapper.FormulaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FormulaServiceImpl implements FormulaService {
    FormulaRepository formulaRepository;
    FormulaMapper formulaMapper;

    @Override
    public Formula persistFormula(FormulaInput formulaInput) {
        return formulaRepository.save(
                formulaMapper.mapToFormula(formulaInput)
        );
    }

    @Override
    public Formula findFormulaById(Long id) {
        return formulaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Formula> findFormulaByCode(String code) {
        return formulaRepository.findFormulaByCode(code);
    }

    @Override
    public Formula findFormulaLastVersion(String code) {
        return formulaRepository.findTopByVersionAndCode(code)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Formula findFormulaByCodeAndVersion(String code, Long version) {
        return formulaRepository.findFormulaByVersionAndCode(code, version)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Formula modifyFormulaByCode(String code, String formula) {
        Formula dbFormula = formulaRepository.findTopByVersionAndCode(code)
                .stream()
                .findFirst()
                .orElse(null);
        if (dbFormula == null)
            return null;
        Formula clonedFormula = formulaMapper.cloneFormula(dbFormula);
        clonedFormula.setId(null);
        clonedFormula.setVersion(dbFormula.getVersion() + 1);
        clonedFormula.setFormula(formula);
        clonedFormula.setOutputElementValueList(new ArrayList<>());
        return formulaRepository.save(clonedFormula);
    }

    @Override
    public void deleteFormulaById(Long id) {
        formulaRepository.deleteById(id);
    }
}
