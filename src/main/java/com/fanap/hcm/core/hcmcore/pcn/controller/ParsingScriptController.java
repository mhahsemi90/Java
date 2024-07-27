package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.token.ScriptDto;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IParsingScriptService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("script/parse")
public class ParsingScriptController {
    private final IParsingScriptService parsingScriptService;

    @PostMapping()
    public ResponseEntity<List<Statement>> parsingScript(@RequestBody()ScriptDto script) {
        List<Statement> parsingData = parsingScriptService.parsing(script.getScript());
        return new ResponseEntity<>(parsingData, HttpStatus.OK);
    }
}
