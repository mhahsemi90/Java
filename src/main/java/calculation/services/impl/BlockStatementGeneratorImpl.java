package calculation.services.impl;

import calculation.services.dto.statement.BlockStatement;
import calculation.services.dto.statement.Statement;
import calculation.services.dto.token.Token;
import calculation.services.interfaces.StatementGenerator;
import org.apache.commons.collections4.CollectionUtils;
import calculation.services.dto.statement.StatementType;

import java.util.List;

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
            if (CollectionUtils.isNotEmpty(statementList) && statementList.get(0).getType() == StatementType.HANDLED_ERROR)
                result = statementList.get(1);
            else
                result = new BlockStatement(statementList);
        } else {
            this.throwHandledError(selectedTokenList, "{");
        }
        return result;
    }
}
