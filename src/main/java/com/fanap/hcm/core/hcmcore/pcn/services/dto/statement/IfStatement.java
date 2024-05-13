package com.fanap.hcm.core.hcmcore.pcn.services.dto.statement;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.Expression;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IfStatement extends Statement {
    private Expression test;
    private Statement consequent;
    private Statement alternate;

    public IfStatement() {
        super(StatementType.IF);
    }
}
