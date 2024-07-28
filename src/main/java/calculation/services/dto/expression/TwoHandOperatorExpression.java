package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TwoHandOperatorExpression extends OperatorExpression {
    private Expression leftChild;
    private Expression rightChild;
}
