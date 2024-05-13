package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariableDeclaratorExpression extends Expression {
    private Expression variableName;
    private Expression initiateValue;

    public VariableDeclaratorExpression() {
        super(ExpressionType.VARIABLE_DECLARATOR_EXPRESSION);
    }

    public VariableDeclaratorExpression(Expression variableName, Expression initiateValue) {
        super(ExpressionType.VARIABLE_DECLARATOR_EXPRESSION);
        this.variableName = variableName;
        this.initiateValue = initiateValue;
    }
}
