package calculation.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InputParameterInput {
    private Long id;
    private String code;
    private String title;
    private String dataType;
}
