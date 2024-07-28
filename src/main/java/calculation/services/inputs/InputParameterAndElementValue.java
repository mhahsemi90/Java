package calculation.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InputParameterAndElementValue {
    private Long inputParameterId;
    private Long elementId;
    private String value;
}
