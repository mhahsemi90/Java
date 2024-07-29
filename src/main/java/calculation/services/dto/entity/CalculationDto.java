package calculation.services.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalculationDto {
    private Long id;
    private Date actionDate;
    private List<OutputElementTransactionDto> outputElementTransactionList = new ArrayList<>();
    private List<InputElementTransactionDto> inputElementTransactionList = new ArrayList<>();
}
