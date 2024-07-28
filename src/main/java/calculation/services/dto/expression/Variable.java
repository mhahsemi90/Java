package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Variable extends Expression {
    private String idName;

    public Variable(String idName) {
        super(ExpressionType.VARIABLE_EXPRESSION);
        this.idName = idName;
    }
}
