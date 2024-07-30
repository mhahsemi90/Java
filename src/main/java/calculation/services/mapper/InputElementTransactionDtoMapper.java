package calculation.services.mapper;

import calculation.repository.entity.InputElementTransaction;
import calculation.services.dto.entity.InputElementTransactionDto;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputElementTransactionDtoMapper {
    InputElementTransaction mapToInputElementTransaction(
            InputElementTransactionDto inputElementTransactionDto,
            @Context CycleAvoidingMappingContext context
    );
}
