package calculation.services.impl;

import calculation.services.dto.expression.TwoHandOperatorExpression;
import calculation.services.dto.expression.VariableDeclaratorExpression;
import calculation.services.dto.statement.ExpressionStatement;
import calculation.services.dto.statement.Statement;
import calculation.services.dto.statement.VariableDeclarationStatement;
import calculation.services.dto.token.Token;
import calculation.services.dto.token.TokenType;
import calculation.services.exception.HandledError;
import calculation.services.interfaces.StatementGenerator;

import java.util.ArrayList;
import java.util.List;

public class VariableDeclarationStatementGeneratorImpl implements StatementGenerator {
    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        Token token = getFirstTokenThatNotNewLine(selectedTokenList);
        VariableDeclarationStatement result = new VariableDeclarationStatement();
        StatementGenerator generator = new ExpressionStatementGeneratorImpl();
        if (token.getTokenType() == TokenType.KEYWORD && isVariableKeyword(token.getValue())) {
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
