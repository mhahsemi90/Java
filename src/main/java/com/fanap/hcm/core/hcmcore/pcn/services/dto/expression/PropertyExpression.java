package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyExpression extends Expression {
    private Variable key;
    private Expression value;

    public PropertyExpression() {
        super(ExpressionType.PROPERTY_EXPRESSION);
    }
}
