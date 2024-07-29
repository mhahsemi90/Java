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
public class OutputParameterDto {
    private Long id;
    private String code;
    private String title;
    private String dataType;
    private List<OutputElementValueDto> outputElementValueList = new ArrayList<>();
}
