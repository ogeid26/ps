package org.austral.edu;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner {

    @Test
    public void testDivideList() {
        ArrayList<Token> tokens = new ArrayList<>();
        tokens.add(Token.KEYWORD);
        tokens.add(Token.IDENTIFIER);
        tokens.add(Token.DECLARATION);
        tokens.add(Token.STRINGTYPE);
        tokens.add(Token.ASSIGNATION);
        tokens.add(Token.STRING);
        tokens.add(Token.SEMICOLON);
        tokens.add(Token.KEYWORD);
        tokens.add(Token.IDENTIFIER);
        tokens.add(Token.DECLARATION);
        tokens.add(Token.STRINGTYPE);
        tokens.add(Token.ASSIGNATION);
        tokens.add(Token.STRING);
        tokens.add(Token.SEMICOLON);
        assertEquals(2, new Parser().parse(tokens));
    }
}
