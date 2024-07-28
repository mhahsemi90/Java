package calculation.services.mapper;

import calculation.repository.entity.Formula;
import calculation.services.inputs.FormulaInput;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class FormulaMapper {
    public abstract Formula mapToFormula(FormulaInput formulaInput);

    public abstract Formula cloneFormula(Formula formula);

    @AfterMapping
    protected void setNewVersion(@NotNull @MappingTarget Formula formula) {
        formula.setVersion(1L);
    }
}
