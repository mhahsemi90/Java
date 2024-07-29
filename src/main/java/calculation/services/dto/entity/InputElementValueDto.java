package calculation.services.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputElementValueDto {
    private Long id;
    private String data;
    private String dataType;
    private InputParameterDto inputParameter;
    private InputElementTransactionDto inputElementTransaction;
}
