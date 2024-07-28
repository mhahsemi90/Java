package calculation.services.impl;

import calculation.services.dto.statement.Statement;
import calculation.services.interfaces.ParsingScriptService;
import calculation.services.interfaces.StatementGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsingScriptServiceImpl implements ParsingScriptService {
    private final StatementGenerator statementGenerator;

    public ParsingScriptServiceImpl() {
        this.statementGenerator = new MainStatementGeneratorImpl();
    }

    @Override
    public List<Statement> parsing(String script) {
        return statementGenerator.parsing(script);
    }
}
