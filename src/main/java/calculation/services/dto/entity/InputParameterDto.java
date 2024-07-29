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
public class InputParameterDto {
    private Long id;
    private String code;
    private String title;
    private String dataType;
    private List<InputElementValueDto> inputElementValueList = new ArrayList<>();
}
