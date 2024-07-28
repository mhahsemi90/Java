package calculation.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Literal extends Expression {
    private String value;

    public Literal() {
        super(ExpressionType.LITERAL_EXPRESSION);
    }
}
