package org.austral.edu;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Lexer lexer = new LexerImpl();
        ArrayList<Token> tokens =  lexer.lex("let x: Number = 19;");

        for (Token token: tokens) {
            System.out.println(token.tokenType);
        }
    }
}