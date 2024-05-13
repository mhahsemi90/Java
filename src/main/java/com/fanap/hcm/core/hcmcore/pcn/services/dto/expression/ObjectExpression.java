package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ObjectExpression extends Expression {
    private List<Expression> propertyList;

    public ObjectExpression() {
        super(ExpressionType.OBJECT_EXPRESSION);
        this.propertyList = new ArrayList<>();
    }

}
