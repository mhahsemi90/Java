package calculation.services.impl;

import calculation.services.dto.statement.Statement;
import calculation.services.interfaces.IParsingScriptService;
import calculation.services.interfaces.StatementGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IParsingScriptServiceImpl implements IParsingScriptService {
    private final StatementGenerator statementGenerator;

    public IParsingScriptServiceImpl() {
        this.statementGenerator = new MainStatementGeneratorImpl();
    }

    @Override
    public List<Statement> parsing(String script) {
        return statementGenerator.parsing(script);
    }
}
