package calculation.services.dto.entity;

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
public class ElementDto {
    private Long id;
    private String vrId;
    private ElementTypeDto elementType;
    private List<OutputElementTransactionDto> outputElementTransactionList = new ArrayList<>();
    private List<InputElementTransactionDto> inputElementTransactionList = new ArrayList<>();
}