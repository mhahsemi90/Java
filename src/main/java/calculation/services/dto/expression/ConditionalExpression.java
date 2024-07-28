package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConditionalExpression extends Expression {
    private Expression test;
    private Expression consequent;
    private Expression alternate;

    public ConditionalExpression() {
        super(ExpressionType.CONDITIONAL_EXPRESSION);
    }

}
