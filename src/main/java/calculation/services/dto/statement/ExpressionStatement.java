package calculation.services.dto.statement;

import calculation.services.dto.expression.Expression;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement() {
        super(StatementType.EXPRESSION);
    }
}
