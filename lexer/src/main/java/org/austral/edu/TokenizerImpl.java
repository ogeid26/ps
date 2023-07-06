package org.austral.edu;

import exceptions.UnknownTokenException;

import java.util.Arrays;

public class TokenizerImpl implements Tokenizer {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row){
        for (TokenType type: TokenType.values()) {
            if (type.getName().equals("")) {
                continue;
            }
            if (type.getName().equals(currentString)){
                return new Token(type, currentString);
            }
            if (currentString.matches("^[0-9]+.*[0-9]*")) {
                return new Token(TokenType.NUMBER, currentString);
            }
            if (currentString.contains("\"") || currentString.contains("'")) {
                return new Token(TokenType.STRING, currentString);
            }
        }
        return new Token(TokenType.IDENTIFIER, currentString);
    }

}

