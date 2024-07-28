package calculation.services.interfaces;

import calculation.services.dto.statement.Statement;

import java.util.List;

public interface ParsingScriptService {
    List<Statement> parsing(String script);
}
