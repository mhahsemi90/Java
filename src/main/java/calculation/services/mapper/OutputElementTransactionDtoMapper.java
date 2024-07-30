package calculation.services.mapper;

import calculation.repository.entity.OutputElementTransaction;
import calculation.services.dto.entity.OutputElementTransactionDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputElementTransactionDtoMapper {
    OutputElementTransaction mapToOutputElementTransaction(
            OutputElementTransactionDto outputElementTransactionDto,
            @Context CycleAvoidingMappingContext context
    );
}
