package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberExpression extends Expression {
    Expression object;
    Expression property;

    public MemberExpression() {
        super(ExpressionType.MEMBER_EXPRESSION);
    }
}
