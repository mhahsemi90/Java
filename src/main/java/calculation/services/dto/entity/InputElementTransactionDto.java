package calculation.services.dto.entity;

import calculation.repository.entity.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InputElementTransactionDto {
    private Long id;
    private Element element;
    private CalculationDto calculation;
    private List<InputElementValueDto> inputElementValueList = new ArrayList<>();
}
