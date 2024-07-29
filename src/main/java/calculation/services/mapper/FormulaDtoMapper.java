package calculation.services.mapper;

import calculation.repository.entity.Formula;
import calculation.services.dto.entity.FormulaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormulaDtoMapper {
    Formula mapToFormula(FormulaDto formulaDto);
    FormulaDto mapToFormulaDto(Formula formula);
}
