package org.austral.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testDivideList() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(new Token(TokenType.KEYWORD, "let"));
        tokens.add(new Token(TokenType.IDENTIFIER, "name"));
        tokens.add(new Token(TokenType.DECLARATION, ":"));
        tokens.add(new Token(TokenType.STRINGTYPE, "String"));
        tokens.add(new Token(TokenType.SEMICOLON, ";"));
        tokens.add(new Token(TokenType.KEYWORD, "let"));
        tokens.add(new Token(TokenType.IDENTIFIER, "lastName"));
        tokens.add(new Token(TokenType.DECLARATION, ":"));
        tokens.add(new Token(TokenType.STRINGTYPE, "Number"));
        tokens.add(new Token(TokenType.SEMICOLON, ";"));
        tokens.add(new Token(TokenType.IDENTIFIER, "lastName"));
        tokens.add(new Token(TokenType.DECLARATION, ":"));
        tokens.add(new Token(TokenType.STRINGTYPE, "Number"));
        tokens.add(new Token(TokenType.SEMICOLON, ";"));
    }
}
