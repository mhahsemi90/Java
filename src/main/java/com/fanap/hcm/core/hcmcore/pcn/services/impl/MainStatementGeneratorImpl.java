package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.services.dto.statement.Statement;
import com.fanap.hcm.core.hcmcore.pcn.services.dto.token.Token;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.StatementGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fanap.hcm.core.hcmcore.pcn.services.dto.token.TokenType.*;
@Service
public class MainStatementGeneratorImpl implements StatementGenerator {

    @Override
    public List<Statement> parsing(String script) {
        List<Character> characterList = new ArrayList<>();
        for (char c : script.toCharArray()) {
            characterList.add(c);
        }
        List<Character> mainSeparator = getAllSeparatorCharacter();
        List<Character> noChar = getNoChar();
        List<String> mainFormula = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean isFirstTextEnd = false;
        for (int i = 0, characterListSize = characterList.size(); i < characterListSize; i++) {
            Character character = characterList.get(i);
            if (character.equals('\\')) {
                i++;
                builder.append("\\");
                if (i < characterListSize) {
                    character = characterList.get(i);
                    builder.append(character);
                }
            } else if (isFirstTextEnd) {
                builder.append(character);
                if (character.equals('\'') || character.equals('"')) {
                    mainFormula.add(builder.toString());
                    builder = new StringBuilder();
                    isFirstTextEnd = false;
                }
            } else {
                if (character.equals('\'') || character.equals('"')) {
                    if (StringUtils.isNotBlank(builder))
                        mainFormula.add(builder.toString().trim());
                    builder = new StringBuilder();
                    builder.append(character);
                    isFirstTextEnd = true;
                } else {
                    if (mainSeparator.contains(character)) {
                        if (StringUtils.isNotBlank(builder))
                            mainFormula.add(builder.toString().trim());
                        if (!character.equals(' ') ||
                                mainFormula.size() > 0 && !mainFormula.get(mainFormula.size() - 1).equals(" ")
                        )
                            mainFormula.add(String.valueOf(character));
                        builder = new StringBuilder();
                    } else {
                        if (!noChar.contains(character))
                            builder.append(character);
                    }
                }
            }
        }
        if (builder.length() > 0) {
            mainFormula.add(builder.toString());
        }
        int size = mainFormula.size();
        List<String> stringTokenList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String token = mainFormula.remove(0);
            if (getHaveSequenceString().contains(token)) {
                String typeSequence = "NONE";
                if (mainFormula.size() > 2) {
                    String sequence = token + mainFormula.get(0) + mainFormula.get(1) + mainFormula.get(2);
                    if (sequence.equals(">>>=")) {
                        typeSequence = "FOUR";
                        mainFormula.remove(0);
                        mainFormula.remove(0);
                        i += 2;
                        token = sequence;
                    }
                }
                if (mainFormula.size() > 1 && !typeSequence.equalsIgnoreCase("FOUR")) {
                    String sequence = token + mainFormula.get(0) + mainFormula.get(1);
                    if (sequence.equals("**=") || sequence.equals("===") ||
                            sequence.equals(">>=") || sequence.equals("<<=") ||
                            sequence.equals("!==") || sequence.equals(">>>")) {
                        typeSequence = "THREE";
                        mainFormula.remove(0);
                        mainFormula.remove(0);
                        i += 2;
                        token = sequence;
                    }
                }
                if (mainFormula.size() > 0 && !typeSequence.equalsIgnoreCase("THREE")) {
                    String sequence = token + mainFormula.get(0);
                    if (sequence.equals("++") || sequence.equals("--") ||
                            sequence.equals("**") || sequence.equals("+=") ||
                            sequence.equals("-=") || sequence.equals("/=") ||
                            sequence.equals("%=") || sequence.equals("==") ||
                            sequence.equals("!=") || sequence.equals(">=") ||
                            sequence.equals("<=") || sequence.equals("&&") ||
                            sequence.equals("||") || sequence.equals("<<") ||
                            sequence.equals("//") || sequence.equals("/*") ||
                            sequence.equals("*/") || sequence.equals(">>")) {
                        mainFormula.remove(0);
                        i += 1;
                        token = sequence;
                    }
                }
            }
            stringTokenList.add(token);
        }
        boolean isRemove = false;
        for (int i = stringTokenList.size() - 1; i >= 0; i--) {
            if (isRemove && (stringTokenList.get(i).equals(" ") || stringTokenList.get(i).equals("\n")))
                stringTokenList.remove(i);
            if (stringTokenList.get(i).equals("(") ||
                    stringTokenList.get(i).equals("[")) {
                isRemove = true;
            }
            if (!(stringTokenList.get(i).equals("(") ||
                    stringTokenList.get(i).equals("[") ||
                    stringTokenList.get(i).equals(" ") ||
                    stringTokenList.get(i).equals("\n"))) {
                isRemove = false;
            }
        }
        List<Token> tokenList = new ArrayList<>();
        List<String> allSeparatorTokenStringList = getAllOperatorAndNotOperatorSeparator();
        int lineNumber = 1;
        int level = 0;
        for (String s1 : stringTokenList) {
            Token token = new Token();
            if (getAllKeyword().contains(s1)) {
                token.setTokenType(KEYWORD);
                token.setValue(s1);
                token.setLevel(level);
            } else if (allSeparatorTokenStringList.contains(s1)) {
                token.setTokenType(PUNCTUATOR);
                if (s1.equals("]") || s1.equals(")") || s1.equals("}"))
                    level--;
                token.setLevel(level);
                if (s1.equals("[") || s1.equals("(") || s1.equals("{"))
                    level++;
                if (s1.equals("\n")) {
                    token.setValue(String.valueOf(lineNumber++));
                    token.setTokenType(NEW_LINE);
                } else {
                    token.setValue(s1);
                }
            } else if (
                    s1.startsWith("'") ||
                            s1.startsWith("\"") ||
                            StringUtils.isNumeric(s1) ||
                            getAllLiteralKeyword().contains(s1)) {
                token.setTokenType(LITERAL);
                token.setValue(s1);
                token.setLevel(level);
            } else if (StringUtils.isNotBlank(s1)) {
                token.setTokenType(VARIABLE);
                token.setValue(s1);
                token.setLevel(level);
            }
            if (token.getTokenType() != null)
                tokenList.add(token);
        }
        if (CollectionUtils.isNotEmpty(tokenList)
                && tokenList.get(tokenList.size() - 1).getTokenType() != NEW_LINE)
            tokenList.add(new Token(NEW_LINE, String.valueOf(lineNumber), 0));
        List<Statement> statementList = getAllStatementFromTokenList(tokenList);
        if(CollectionUtils.isNotEmpty(statementList) &&
                statementList.get(statementList.size()-1)==null)
            statementList.remove(statementList.size()-1);
        return statementList;
    }

    @Override
    public List<Statement> getAllStatementFromTokenList(List<Token> tokenList) {
        return StatementGenerator.super.getAllStatementFromTokenList(tokenList);
    }

    @Override
    public Statement generate(List<Token> selectedTokenList, List<Token> tokenList) {
        StatementGenerator statementGenerator = new ExpressionStatementGeneratorImpl();
        mainBlock:
        {
            if (CollectionUtils.isNotEmpty(selectedTokenList) &&
                    isVariableKeyword(selectedTokenList.get(0).getValue())) {
                statementGenerator = new VariableDeclarationStatementGeneratorImpl();
                break mainBlock;
            }
            if (CollectionUtils.isNotEmpty(selectedTokenList) &&
                    selectedTokenList.get(0).getValue().equals("if")) {
                statementGenerator = new IfStatementGeneratorImpl();
            }
        }
        return statementGenerator.generate(selectedTokenList, tokenList);
    }

    private List<Character> getAllSeparatorCharacter() {
        List<Character> characterList = new ArrayList<>();
        characterList.add('!');
        characterList.add('\n');
        characterList.add('&');
        characterList.add('|');
        characterList.add('(');
        characterList.add(')');
        characterList.add('+');
        characterList.add('-');
        characterList.add('*');
        characterList.add('/');
        characterList.add(',');
        characterList.add(':');
        characterList.add(';');
        characterList.add('=');
        characterList.add('>');
        characterList.add('<');
        characterList.add('?');
        characterList.add(' ');
        characterList.add('{');
        characterList.add('}');
        characterList.add('[');
        characterList.add(']');
        return characterList;
    }

    private List<String> getHaveSequenceString() {
        List<String> haveSequenceStringList = new ArrayList<>();
        haveSequenceStringList.add("+");
        haveSequenceStringList.add("-");
        haveSequenceStringList.add("*");
        haveSequenceStringList.add("/");
        haveSequenceStringList.add("%");
        haveSequenceStringList.add("=");
        haveSequenceStringList.add("!");
        haveSequenceStringList.add("&");
        haveSequenceStringList.add("|");
        haveSequenceStringList.add(">");
        haveSequenceStringList.add("<");
        return haveSequenceStringList;
    }

    private List<String> getAllOperatorAndNotOperatorSeparator() {
        List<String> operatorList = new ArrayList<>();
        operatorList.add("+");
        operatorList.add("-");
        operatorList.add("*");
        operatorList.add("**");
        operatorList.add("/");
        operatorList.add("%");
        operatorList.add("++");
        operatorList.add("--");
        operatorList.add("=");
        operatorList.add("+=");
        operatorList.add("-=");
        operatorList.add("*=");
        operatorList.add("/=");
        operatorList.add("%=");
        operatorList.add("**=");
        operatorList.add("==");
        operatorList.add("===");
        operatorList.add("!=");
        operatorList.add("!==");
        operatorList.add(">");
        operatorList.add("<");
        operatorList.add(">=");
        operatorList.add("<=");
        operatorList.add("!");
        operatorList.add("&&");
        operatorList.add("||");
        operatorList.add("&");
        operatorList.add("|");
        operatorList.add("~");
        operatorList.add("^");
        operatorList.add(">");
        operatorList.add("<<");
        operatorList.add(">>");
        operatorList.add(">>>");
        operatorList.add("\n");
        operatorList.add("(");
        operatorList.add(")");
        operatorList.add(",");
        operatorList.add(".");
        operatorList.add(":");
        operatorList.add(";");
        operatorList.add("{");
        operatorList.add("}");
        operatorList.add("[");
        operatorList.add("]");
        operatorList.add("?");
        return operatorList;
    }

    private List<String> getAllKeyword() {
        List<String> keyWordList = new ArrayList<>();
        keyWordList.add("arguments");
        keyWordList.add("await");
        keyWordList.add("break");
        keyWordList.add("case");
        keyWordList.add("catch");
        keyWordList.add("class");
        keyWordList.add("const");
        keyWordList.add("continue");
        keyWordList.add("debugger");
        keyWordList.add("default");
        keyWordList.add("delete");
        keyWordList.add("do");
        keyWordList.add("else");
        keyWordList.add("enum");
        keyWordList.add("eval");
        keyWordList.add("export");
        keyWordList.add("extends");
        keyWordList.add("finally");
        keyWordList.add("for");
        keyWordList.add("function");
        keyWordList.add("if");
        keyWordList.add("implements");
        keyWordList.add("import");
        keyWordList.add("in");
        keyWordList.add("instanceof");
        keyWordList.add("interface");
        keyWordList.add("let");
        keyWordList.add("new");
        keyWordList.add("package");
        keyWordList.add("private");
        keyWordList.add("protected");
        keyWordList.add("public");
        keyWordList.add("return");
        keyWordList.add("static");
        keyWordList.add("super");
        keyWordList.add("switch");
        keyWordList.add("this");
        keyWordList.add("throw");
        keyWordList.add("try");
        keyWordList.add("typeof");
        keyWordList.add("var");
        keyWordList.add("void");
        keyWordList.add("while");
        keyWordList.add("with");
        keyWordList.add("yield");
        //keyWordList.add("?");
        keyWordList.addAll(getAllLiteralKeyword());
        return keyWordList;
    }

    private List<String> getAllLiteralKeyword() {
        List<String> keyWordList = new ArrayList<>();
        keyWordList.add("false");
        keyWordList.add("true");
        keyWordList.add("null");
        keyWordList.add("undefined");
        return keyWordList;
    }

    private List<Character> getNoChar() {
        List<Character> characterList = new ArrayList<>();
        characterList.add('\n');
        characterList.add('\t');
        return characterList;
    }
}
