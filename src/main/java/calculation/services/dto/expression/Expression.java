package calculation.services.dto.expression;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Expression {
    private ExpressionType type;
    @JsonIgnore
    private Expression parent;

    public Expression(ExpressionType type) {
        this.type = type;
    }
}
