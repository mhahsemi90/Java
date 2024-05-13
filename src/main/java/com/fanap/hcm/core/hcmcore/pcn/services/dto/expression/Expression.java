package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Expression {
    private ExpressionType type;
    private Expression parent;

    public Expression(ExpressionType type) {
        this.type = type;
    }
}
