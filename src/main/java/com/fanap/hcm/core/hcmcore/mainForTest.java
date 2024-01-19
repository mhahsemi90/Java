package com.fanap.hcm.core.hcmcore;

import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.parser.Parser;
import org.openjdk.nashorn.internal.runtime.Context;
import org.openjdk.nashorn.internal.runtime.ErrorManager;
import org.openjdk.nashorn.internal.runtime.Source;
import org.openjdk.nashorn.internal.runtime.options.Options;

import javax.script.ScriptException;
import java.util.List;

public class mainForTest {
    public static void main(String[] args) throws ScriptException {
        Options options = new Options("nashorn");
        options.set("anon.functions", true);
        options.set("parse.only", true);
        options.set("scripting", true);
        ErrorManager errors = new ErrorManager();
        Context context = new Context(options, errors, Thread.currentThread().getContextClassLoader());
        Source source = Source.sourceFor("test", "var a = 10; var b = a + 1;" +
                "function someFunction() { return _LastCalculation(Input = cild,Output = food) + getOb() + 1 }  ");
        Parser parser = new Parser(context.getEnv(), source, errors);
        FunctionNode functionNode = null;
        try {
            functionNode = parser.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Block block = functionNode.getBody();
        List statements = block.getStatements();
        System.out.println("json");
    }
}
