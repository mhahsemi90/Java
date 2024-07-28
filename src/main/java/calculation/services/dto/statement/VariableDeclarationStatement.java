package calculation.services.dto.statement;


import calculation.services.dto.expression.VariableDeclaratorExpression;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VariableDeclarationStatement extends Statement {
    private String kind;
    private List<VariableDeclaratorExpression> declaratorExpressionList;

    public VariableDeclarationStatement() {
        super(StatementType.VARIABLE_DECLARATION);
        setDeclaratorExpressionList(new ArrayList<>());
    }
}
