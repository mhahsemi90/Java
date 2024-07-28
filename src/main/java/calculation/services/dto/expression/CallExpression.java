package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CallExpression extends Expression {
    private Expression callVariableName;
    private List<Expression> argumentList;

    public CallExpression() {
        super(ExpressionType.CALL_EXPRESSION);
        setArgumentList(new ArrayList<>());
    }
}
