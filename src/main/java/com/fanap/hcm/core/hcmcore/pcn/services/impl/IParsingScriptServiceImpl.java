package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IParsingScriptService;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.StatementGenerator;
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
