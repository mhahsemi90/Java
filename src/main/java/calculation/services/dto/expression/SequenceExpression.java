package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SequenceExpression extends Expression {
    List<Expression> expressions;

    public SequenceExpression() {
        super(ExpressionType.SEQUENCE_EXPRESSION);
        setExpressions(new ArrayList<>());
    }
}
