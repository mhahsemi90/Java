package calculation.services.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputParameterIdAndFormula {
    private Long outputParameterId;
    private Long elementId;
    private Long formulaId;
}
