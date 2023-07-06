package org.austral.edu;

public enum TokenType {
    IDENTIFIER(""),
    LET("let"),
    DECLARATION(":"),
    STRING_TYPE("string"),
    NUMBER_TYPE("number"),
    STRING(""),
    NUMBER(""),
    ASSIGNATION("="),
    SEMICOLON(";"),
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    PRINTLN("println"),
    BOOLEAN_TYPE("boolean"),
    BOOLEAN(""),
    IF("if"),
    ELSE("else"),
    READ_INPUT("readInput"),
    CONST("const");

    private final String name;

    TokenType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
