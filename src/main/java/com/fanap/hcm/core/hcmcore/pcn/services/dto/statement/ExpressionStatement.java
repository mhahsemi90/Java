package com.fanap.hcm.core.hcmcore.pcn.services.dto.statement;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.Expression;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement() {
        super(StatementType.EXPRESSION);
    }
}
