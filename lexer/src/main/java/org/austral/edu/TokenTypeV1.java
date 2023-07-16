package org.austral.edu;

public enum TokenTypeV1 implements TokenType {
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
    R_PAR(")"),
    L_PAR("(");

    private final String name;

    TokenTypeV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
