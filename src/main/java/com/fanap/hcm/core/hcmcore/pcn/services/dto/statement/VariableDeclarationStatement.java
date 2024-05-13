package com.fanap.hcm.core.hcmcore.pcn.services.dto.statement;


import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.VariableDeclaratorExpression;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VariableDeclarationStatement extends Statement {
    private String kind;
    private List<VariableDeclaratorExpression> declaratorExpressionList;

    public VariableDeclarationStatement() {
        super(StatementType.VARIABLE_DECLARATION);
        setDeclaratorExpressionList(new ArrayList<>());
    }
}
