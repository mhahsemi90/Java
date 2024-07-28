package calculation.controller;

import calculation.services.dto.statement.Statement;
import calculation.services.dto.token.ScriptDto;
import calculation.services.interfaces.ParsingScriptService;
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
    private final ParsingScriptService parsingScriptService;

    @PostMapping()
    public ResponseEntity<List<Statement>> parsingScript(@RequestBody()ScriptDto script) {
        List<Statement> parsingData = parsingScriptService.parsing(script.getScript());
        return new ResponseEntity<>(parsingData, HttpStatus.OK);
    }
}
