package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;

import java.util.List;

public interface IParsingScriptService {
    List<Statement> parsing(String script);
}
