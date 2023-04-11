package org.austral.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner {

    @Test
    public void testDivideList() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.KEYWORD, "let"));
        tokens.add(new Token(TokenType.IDENTIFIER, "name"));
        tokens.add(new Token(TokenType.DECLARATION, ":"));
        tokens.add(new Token(TokenType.STRINGTYPE, "String"));
        tokens.add(new Token(TokenType.ASSIGNATION, "="));
        tokens.add(new Token(TokenType.STRING, "'Peter'"));
        tokens.add(new Token(TokenType.SEMICOLON, ";"));
        tokens.add(new Token(TokenType.KEYWORD, "let"));
        tokens.add(new Token(TokenType.IDENTIFIER, "lastName"));
        tokens.add(new Token(TokenType.DECLARATION, ":"));
        tokens.add(new Token(TokenType.STRINGTYPE, "String"));
        tokens.add(new Token(TokenType.ASSIGNATION, "="));
        tokens.add(new Token(TokenType.STRING, "'Parker'"));
        tokens.add(new Token(TokenType.SEMICOLON, ";"));
        assertEquals(2, new Parser().parse(tokens));
    }
}
