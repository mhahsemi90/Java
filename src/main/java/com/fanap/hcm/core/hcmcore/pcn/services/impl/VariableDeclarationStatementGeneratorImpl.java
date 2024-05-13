package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.TwoHandOperatorExpression;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.expression.VariableDeclaratorExpression;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.ExpressionStatement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.VariableDeclarationStatement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.token.Token;
import com.fanap.hcm.core.hcmcore.pcn.services.exception.HandledError;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.StatementGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.fanap.hcm.core.hcmcore.pcn.services.dto.token.TokenType.KEYWORD;

public class VariableDeclarationStatementGeneratorImpl implements StatementGenerator {
    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        Token token = getFirstTokenThatNotNewLine(selectedTokenList);
        VariableDeclarationStatement result = new VariableDeclarationStatement();
        StatementGenerator generator = new ExpressionStatementGeneratorImpl();
        if (token.getTokenType() == KEYWORD && isVariableKeyword(token.getValue())) {
            result.setKind(token.getValue());
        } else {
            throw new HandledError(
                    "Token \"" + token.getValue() + "\" not valid Line:" + getLineNumber(selectedTokenList)
            );
        }
        List<List<Token>> listTokens = getSameLevelTokenListSeparateByComma(
                token.getLevel() - 1,
                selectedTokenList
        );
        for (List<Token> listToken : listTokens) {
            TwoHandOperatorExpression expression = (TwoHandOperatorExpression) (
                    (ExpressionStatement) generator
                            .generate(listToken, new ArrayList<>()))
                    .getExpression();
            result.getDeclaratorExpressionList().add(
                    new VariableDeclaratorExpression(expression.getLeftChild(), expression.getRightChild())
            );
        }
        return result;
    }
}
