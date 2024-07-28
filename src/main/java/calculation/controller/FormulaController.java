package calculation.controller;

import calculation.repository.entity.Formula;
import calculation.services.inputs.FormulaInput;
import calculation.services.interfaces.FormulaService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class FormulaController {
    private FormulaService formulaService;

    @MutationMapping
    public Formula persistFormula(@Argument FormulaInput formulaInput) {
        return formulaService.persistFormula(formulaInput);
    }

    @QueryMapping
    public Formula findFormulaById(@Argument Long id) {
        return formulaService.findFormulaById(id);
    }

    @QueryMapping
    public List<Formula> findFormulaByCode(@Argument String code) {
        return formulaService.findFormulaByCode(code);
    }

    @QueryMapping
    public Formula findFormulaLastVersion(@Argument String code) {
        return formulaService.findFormulaLastVersion(code);
    }

    @QueryMapping
    public Formula findFormulaByCodeAndVersion(@Argument String code, @Argument Long version) {
        return formulaService.findFormulaByCodeAndVersion(code, version);
    }

    @MutationMapping
    public Formula modifyFormulaByCode(@Argument String code, @Argument String formula) {
        return formulaService.modifyFormulaByCode(code, formula);
    }

    @MutationMapping
    public Boolean deleteFormulaById(@Argument Long id) {
        formulaService.deleteFormulaById(id);
        return true;
    }
}
