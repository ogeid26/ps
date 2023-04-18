package org.austral.edu;

import exceptions.UnclosedStringLiteralException;

import java.util.ArrayList;
public class LexerImpl implements Lexer{
    public Tokenizer tokenizer;
    @Override
    public ArrayList<Token> lex(String input) {

        ArrayList<Token> tokens = new ArrayList<>();

        int length = input.length();
        for (int i = 0, col = 0, row = 0; i < length; i++ ){
            char current = input.charAt(i);
            col++;

            StringBuilder currentString = new StringBuilder();
            currentString.append(current);
            while (i < length - 1) {

                if (
                        !Character.isLetterOrDigit(current)
                                && current != '.'
                                && current != '_'
                )
                    break;

                char nextChar = input.charAt(i + 1);
                if (Character.isLetterOrDigit(nextChar) || nextChar == '.' || nextChar == '_') {
                    currentString.append(nextChar);
                    current = nextChar;
                    col++;
                    i++;
                } else
                    break;
            }

            if (current == '\n') {
                col = 0;
                row++;
            }
        }
        return tokens;
    }

    // Lexer helper functions
    private int getNextQuoteMark(String input, char currentChar, int i) {
        return currentChar == '"'
                ? (input.substring(i + 1)).indexOf('"')
                : (input.substring(i + 1)).indexOf('\'');
    }



}
