package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.Variable;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.LabeledStatement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.token.Token;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.StatementGenerator;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import static com.fanap.hcm.core.hcmcore.pcn.services.dto.token.TokenType.VARIABLE;

public class LabeledStatementGeneratorImpl implements StatementGenerator {
    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        LabeledStatement labeledStatement = new LabeledStatement();
        if (CollectionUtils.isNotEmpty(selectedTokenList) &&
                selectedTokenList.size() > 2 &&
                selectedTokenList.get(0).getTokenType() == VARIABLE &&
                selectedTokenList.get(1).getValue().equals(":")) {
            Variable variable = getVariableExpression(selectedTokenList, selectedTokenList.get(0).getValue());
            selectedTokenList.remove(0);
            selectedTokenList.remove(0);
            if (selectedTokenList.get(0).getValue().equals("{")) {
                StatementGenerator blockStatementGenerator = new BlockStatementGeneratorImpl();
                Statement blockStatement = blockStatementGenerator.generate(selectedTokenList, tokenList);
                labeledStatement = new LabeledStatement(variable, blockStatement);
            } else {
                StatementGenerator statementGenerator = new MainStatementGeneratorImpl();
                Statement statement = statementGenerator.getFirstStatementFromTokenList(selectedTokenList);
                labeledStatement = new LabeledStatement(variable, statement);
            }
        } else {
            throwHandledError(selectedTokenList, ":");
        }
        return labeledStatement;
    }
}
