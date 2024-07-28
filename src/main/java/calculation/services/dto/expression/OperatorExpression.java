package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorExpression extends Expression {
    private String operator;
}
