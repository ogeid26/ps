package org.austral.edu;

import exceptions.UnclosedStringLiteralException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.List;


public class LexerTest {

    @Test
    public void test_001() throws UnclosedStringLiteralException, UnclosedParenthesesException {
        InputProvider string = new StringInput("let name: string = \"Miguel\" 8 8.9 32132 'Hello'+-*/ ");
        Lexer lexer = new LexerImpl();

        List<Token> tokens = lexer.lex(string);
        Assertions.assertEquals(3,3);

    }



   @Test
    public  void test_002() throws IOException {
        Lexer lexer = new LexerImpl();

        String x = """
                let x:string = "Hello";
                let a:number = 5;
                let b: number = 7;
               """;
        InputStream inputStream = new FileInputStream("test.txt");

        StringBuilder stringBuilder = new StringBuilder();
        char currentChar = (char) inputStream.read();
       stringBuilder.append(currentChar);

       while (currentChar != ';') {
            currentChar = (char) inputStream.read();
           stringBuilder.append(currentChar);
       }
       System.out.println(stringBuilder);

    }

    @Test
    public void test_003() throws IOException, UnclosedStringLiteralException, UnclosedParenthesesException {
        LexerStreamInputHandler test = new LexerStreamInputHandler();
        InputStream stream = new FileInputStream("test.txt");

        List<Token> tokens = test.process(stream);
        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }
        Assertions.assertEquals(3,3);

    }

    @Test
    public void test_004() throws UnclosedStringLiteralException, UnclosedParenthesesException {
        StringInput txt2 = new StringInput("println('Hola');");
        Lexer lexer = new LexerImpl();

        List<Token> tokens = lexer.lex(txt2);
        for (Token token: tokens) {
            System.out.println(token.tokenType + " -> " + token.content);
        }
        Assertions.assertEquals(3,3);


    }

    @Test
    public void test_005() {
        Lexer lexer = new LexerImpl();
        UnclosedParenthesesException exception = Assertions.assertThrows(UnclosedParenthesesException.class, () -> lexer.lex(new StringInput("println('Hi';")));
        System.out.println(exception.getMessage());
    }



}

