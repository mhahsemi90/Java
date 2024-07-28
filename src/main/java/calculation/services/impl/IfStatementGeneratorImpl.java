package calculation.services.impl;

import calculation.services.dto.statement.ExpressionStatement;
import calculation.services.dto.statement.IfStatement;
import calculation.services.dto.statement.Statement;
import calculation.services.dto.token.Token;
import calculation.services.exception.HandledError;
import calculation.services.interfaces.StatementGenerator;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class IfStatementGeneratorImpl implements StatementGenerator {
    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        IfStatement result = new IfStatement();
        StatementGenerator blockStatementGenerator = new BlockStatementGeneratorImpl();
        StatementGenerator expressionStatementGenerator = new ExpressionStatementGeneratorImpl();
        if (selectedTokenList.size() > 1 &&
                selectedTokenList.remove(0).getValue().equals("if")) {
            List<Token> testTokenList = getSameLevelTokenListBetweenTwoParenthesis(
                    selectedTokenList.get(0).getLevel(),
                    selectedTokenList);
            result.setTest(
                    ((ExpressionStatement) expressionStatementGenerator.generate(testTokenList, new ArrayList<>()))
                            .getExpression()
            );
            tokenList.addAll(0, selectedTokenList);
            if (CollectionUtils.isNotEmpty(selectedTokenList)
                    && selectedTokenList.get(0).getValue().equals("{")) {
                List<Token> conquestTokenList = getSameLevelTokenListBetweenTwoBrace(
                        tokenList.get(0).getLevel(),
                        tokenList);
                result.setConsequent(
                        blockStatementGenerator.generate(
                                conquestTokenList, new ArrayList<>()
                        )
                );
            } else {
                result.setConsequent(
                        getFirstStatementFromTokenList(tokenList)
                );
            }
            if (CollectionUtils.isNotEmpty(tokenList)
                    && tokenList.get(0).getValue().equals("else")) {
                tokenList.remove(0);
                if (CollectionUtils.isNotEmpty(selectedTokenList)
                        && selectedTokenList.get(0).getValue().equals("{")) {
                    List<Token> alternateTokenList = getSameLevelTokenListBetweenTwoBrace(
                            tokenList.get(0).getLevel(),
                            tokenList);
                    result.setAlternate(
                            blockStatementGenerator.generate(
                                    alternateTokenList, new ArrayList<>()
                            )
                    );
                } else {
                    result.setAlternate(
                            getFirstStatementFromTokenList(tokenList)
                    );
                }
            }
        }
        if (result.getTest() == null || result.getConsequent() == null)
            throw new HandledError("Unexpected end of input line:" + getLineNumber(tokenList));
        return result;
    }
}
