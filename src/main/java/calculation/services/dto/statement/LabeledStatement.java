package calculation.services.dto.statement;

import calculation.services.dto.expression.Expression;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabeledStatement extends Statement {
    private Expression label;
    private Statement body;

    public LabeledStatement(Expression label, Statement body) {
        super(StatementType.LABEL);
        this.label = label;
        this.body = body;
    }

    public LabeledStatement() {
        super(StatementType.LABEL);
    }
}
