package calculation.services.mapper;

import calculation.repository.entity.Calculation;
import calculation.services.dto.entity.CalculationDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CalculationDtoMapper {
    Calculation mapToCalculation(
            CalculationDto calculationDto,
            @Context CycleAvoidingMappingContext context
    );

    CalculationDto mapToCalculationDto(
            Calculation calculation,
            @Context CycleAvoidingMappingContext context
    );
}
