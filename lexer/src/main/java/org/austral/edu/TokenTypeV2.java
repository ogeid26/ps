package org.austral.edu;

public enum TokenTypeV2 implements TokenType {
    BOOLEAN_TYPE("boolean"),
    BOOLEAN(""),
    IF("if"),
    ELSE("else"),
    READ_INPUT("readInput"),
    CONST("const"),
    L_BRACES("{"),
    R_BRACES("}");

    private final String name;

    TokenTypeV2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
