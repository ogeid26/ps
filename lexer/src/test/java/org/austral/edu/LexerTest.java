package org.austral.edu;

import exceptions.UnclosedStringLiteralException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class LexerTest {

    @Test
    public void test001_lexer() throws UnclosedStringLiteralException {
        InputProvider string = new StringInput("let name: string = \"Miguel\" 8 8.9 32132 'Hello'+-*/ ");
        Lexer lexer = new LexerImpl();

        List<Token> tokens = lexer.lex(string);
        Assertions.assertEquals(3,3);

    }



}

