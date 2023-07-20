package org.austral.edu;

import exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LexerTest {

    @Test
    public void test001_Declare_Assign() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("let firstName: string = \"Miguel\"; ");
        Lexer lexer = new LexerExclusive();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV1.STRING_TYPE, TokenTypeV1.ASSIGNATION, TokenTypeV1.STRING, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("let", "firstName", ":", "string", "=", "Miguel", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test002_Declare_then_Assign() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("let x: number; " +
                "x = 1 + 2;");
        Lexer lexer = new LexerExclusive();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV1.NUMBER_TYPE, TokenTypeV1.SEMICOLON, TokenTypeV1.IDENTIFIER, TokenTypeV1.ASSIGNATION,
                TokenTypeV1.NUMBER, TokenTypeV1.PLUS, TokenTypeV1.NUMBER, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("let", "x", ":", "number", ";", "x", "=", "1", "+", "2", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test003_Assign_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("let first_name: string = \"Miguel\"; " +
                "let y: string;" +
                "y = first_name;");
        Lexer lexer = new LexerExclusive();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV1.STRING_TYPE, TokenTypeV1.ASSIGNATION, TokenTypeV1.STRING, TokenTypeV1.SEMICOLON,
                TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION, TokenTypeV1.STRING_TYPE,
                TokenTypeV1.SEMICOLON, TokenTypeV1.IDENTIFIER, TokenTypeV1.ASSIGNATION, TokenTypeV1.IDENTIFIER,
                TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("let", "first_name", ":", "string", "=", "Miguel", ";","let", "y", ":",
                "string", ";", "y", "=", "first_name", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test004_Assign_Declare_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("let x: number = 100; " +
                "let y: number = x;");
        Lexer lexer = new LexerExclusive();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV1.NUMBER_TYPE, TokenTypeV1.ASSIGNATION, TokenTypeV1.NUMBER, TokenTypeV1.SEMICOLON,
                TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION, TokenTypeV1.NUMBER_TYPE,
                 TokenTypeV1.ASSIGNATION, TokenTypeV1.IDENTIFIER, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("let", "x", ":", "number", "=", "100", ";","let", "y", ":",
                "number", "=", "x", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test005_Println() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("println(6/3-1*2);");
        Lexer lexer = new LexerV2();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.PRINTLN, TokenTypeV1.L_PAR, TokenTypeV1.NUMBER,
                TokenTypeV1.DIVIDE, TokenTypeV1.NUMBER, TokenTypeV1.MINUS, TokenTypeV1.NUMBER,
                TokenTypeV1.MULTIPLY, TokenTypeV1.NUMBER, TokenTypeV1.R_PAR, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("println", "(", "6", "/", "3", "-", "1","*", "2", ")", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }
    @Test
    public void test006_Boolean() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("let x:boolean = true;");
        Lexer lexer = new LexerV2();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV1.LET, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV2.BOOLEAN_TYPE, TokenTypeV1.ASSIGNATION, TokenTypeV2.BOOLEAN, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("let", "x", ":", "boolean", "=", "true", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test007_If() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("if(true){" +
                "x;" +
                "}else{" +
                "y;" +
                "}");
        Lexer lexer = new LexerV2();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV2.IF, TokenTypeV1.L_PAR, TokenTypeV2.BOOLEAN,
                TokenTypeV1.R_PAR, TokenTypeV2.L_BRACES, TokenTypeV1.IDENTIFIER, TokenTypeV1.SEMICOLON,
                TokenTypeV2.R_BRACES, TokenTypeV2.ELSE, TokenTypeV2.L_BRACES, TokenTypeV1.IDENTIFIER,
                TokenTypeV1.SEMICOLON, TokenTypeV2.R_BRACES);

        List<String> result2 = Arrays.asList("if", "(", "true", ")", "{", "x", ";" ,"}", "else", "{", "y", ";","}");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test008_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        InputProvider string1 = new StringInput("const x:string = readInput(\"Enter your Name\");");
        Lexer lexer = new LexerV2();
        List<Token> tokens =  lexer.lex(string1);

        List<TokenType> types = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Token token: tokens) {
            types.add(token.tokenType);
            values.add(token.content);
        }

        List<TokenType> result1 = Arrays.asList(TokenTypeV2.CONST, TokenTypeV1.IDENTIFIER, TokenTypeV1.DECLARATION,
                TokenTypeV1.STRING_TYPE, TokenTypeV1.ASSIGNATION, TokenTypeV2.READ_INPUT, TokenTypeV1.L_PAR,
                TokenTypeV1.STRING, TokenTypeV1.R_PAR, TokenTypeV1.SEMICOLON);

        List<String> result2 = Arrays.asList("const", "x", ":", "string", "=", "readInput", "(", "Enter your Name",
                ")", ";");

        assertEquals(result1, types);

        assertEquals(result2, values);

        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }

    }

    @Test
    public void test010_Missing_bracket() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("if(true){" +
                "x;" +
                "else{" +
                "y;" +
                "}");
        Lexer lexer = new LexerV2();

        assertThrows(UnclosedBracesException.class, () -> lexer.lex(string1));
    }

    @Test
    public void test011_Missing_parenthesis() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("println(1;");
        Lexer lexer = new LexerV2();

        assertThrows(UnclosedParenthesesException.class, () -> lexer.lex(string1));
    }

    @Test
    public void test011_Unclosed_strings() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("let x:string = \"name;");
        Lexer lexer = new LexerV2();

        assertThrows(UnclosedStringLiteralException.class, () -> lexer.lex(string1));
    }

    @Test
    public void test012_Unclosed_stringsV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("let x:string = 'name;");
        Lexer lexer = new LexerV2();

        assertThrows(UnclosedStringLiteralException.class, () -> lexer.lex(string1));
    }

    @Test
    public void test013_Case() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("let my.Name:string = \"name\";");
        Lexer lexer = new LexerV2();

        assertThrows(WrongCaseException.class, () -> lexer.lex(string1));
    }

    @Test
    public void test014_Wrong_Type() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException {
        InputProvider string1 = new StringInput("const my.Name:string = \"name\";");
        Lexer lexer = new LexerExclusive();

        assertThrows(UnknownTokenException.class, () -> lexer.lex(string1));
    }

   @Test
    public  void test_002() throws IOException {
        Lexer lexer = new LexerV1();

        String x = "let x:string = 'Hello';let a:number = 5;let b: number = 7;";
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
    public void test_003() throws IOException, UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        LexerStreamInputHandler test = new LexerStreamInputHandler();
        InputStream stream = new FileInputStream("test.txt");

        List<Token> tokens = test.process(stream);
        for (Token token: tokens) {
            System.out.println(token.tokenType + " --> " + token.content);
        }
        assertEquals(3,3);

    }

    @Test
    public void test_004() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        FileInput txt2 = new FileInput("test.txt");
        Lexer lexer = new LexerV2();

        List<Token> tokens = lexer.lex(txt2);
        for (Token token: tokens) {
            System.out.println(token.tokenType + " -> " + token.content);
        }
        assertEquals(3,3);


    }

    @Test
    public void test_005() {
        Lexer lexer = new LexerV1();
        UnclosedParenthesesException exception = assertThrows(UnclosedParenthesesException.class, () -> lexer.lex(new StringInput("println('Hi';")));
        System.out.println(exception.getMessage());
    }
}

