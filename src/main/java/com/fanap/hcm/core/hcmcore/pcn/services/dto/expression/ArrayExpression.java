package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArrayExpression extends Expression {
    private List<Expression> elementList;

    public ArrayExpression() {
        super(ExpressionType.ARRAY_EXPRESSION);
        setElementList(new ArrayList<>());
    }
}
