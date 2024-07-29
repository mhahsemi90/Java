package calculation.services.mapper;

import calculation.repository.entity.Calculation;
import calculation.repository.entity.ElementType;
import calculation.repository.entity.OutputElementTransaction;
import calculation.services.dto.entity.CalculationDto;
import calculation.services.dto.entity.OutputElementTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CalculationDtoMapper {
    public abstract Calculation mapToCalculation(CalculationDto calculationDto);
    public abstract CalculationDto mapToCalculationDto(Calculation calculation);

    @Named("fillOutputElementTransactionList")
    public List<OutputElementTransaction> fillOutputElementTransactionList(
            List<OutputElementTransactionDto> outputElementTransactionDtoList) {
        return new ArrayList<>();
    }
}
