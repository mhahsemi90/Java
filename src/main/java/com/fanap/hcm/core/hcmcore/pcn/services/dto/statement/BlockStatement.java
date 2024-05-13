package com.fanap.hcm.core.hcmcore.pcn.services.dto.statement;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlockStatement extends Statement {
    private List<Statement> body;

    public BlockStatement() {
        super(StatementType.BLOCK);
        setBody(new ArrayList<>());
    }

    public BlockStatement(List<Statement> body) {
        super(StatementType.BLOCK);
        setBody(body);
    }
}
