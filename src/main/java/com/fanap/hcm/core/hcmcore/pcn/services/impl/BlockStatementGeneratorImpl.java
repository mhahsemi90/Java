package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.BlockStatement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.token.Token;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.StatementGenerator;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import static com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.StatementType.HANDLED_ERROR;

public class BlockStatementGeneratorImpl implements StatementGenerator {
    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        Statement result = new Statement();
        if (selectedTokenList.size() > 1 &&
                selectedTokenList.get(0).getValue().equals("{") &&
                selectedTokenList.get(selectedTokenList.size() - 1).getValue().equals("}")) {
            StatementGenerator generator = new MainStatementGeneratorImpl();
            List<Statement> statementList = generator.getAllStatementFromTokenList(
                    selectedTokenList.subList(1, selectedTokenList.size() - 1)
            );
            if (CollectionUtils.isNotEmpty(statementList) && statementList.get(0).getType() == HANDLED_ERROR)
                result = statementList.get(1);
            else
                result = new BlockStatement(statementList);
        } else {
            this.throwHandledError(selectedTokenList, "{");
        }
        return result;
    }
}
