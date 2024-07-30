package calculation.services.mapper;

import calculation.repository.entity.Formula;
import calculation.services.dto.entity.FormulaDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormulaDtoMapper {
    Formula mapToFormula(
            FormulaDto formulaDto,
            @Context CycleAvoidingMappingContext context
    );

    FormulaDto mapToFormulaDto(
            Formula formula,
            @Context CycleAvoidingMappingContext context
    );
}
