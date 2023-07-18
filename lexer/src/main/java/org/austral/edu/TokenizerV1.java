package org.austral.edu;

import java.util.ArrayList;

public class TokenizerV1 implements Tokenizer {
    boolean active;
    ArrayList<TokenType> tokens = new ArrayList<>();

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        for (TokenTypeV1 type: TokenTypeV1.values()) {
            if (type.getName().equals("")) {
                continue;
            }
            if (type.getName().equals(currentString)){
                return new Token(type, currentString);
            }
            if (currentString.matches("^[0-9]+.?[0-9]*")) {
                return new Token(TokenTypeV1.NUMBER, currentString);
            }
            if (currentString.contains("\"") || currentString.contains("'")) {
                return new Token(TokenTypeV1.STRING, currentString);
            }
        }
        if (checkCase(currentString)) {
            return new Token(TokenTypeV1.IDENTIFIER, currentString);
        }else{
            throw new RuntimeException("Case Error");
        }
    }

    private boolean checkCase(String word) {
        if (word.matches("^[a-z][a-zA-Z0-9]*$")) {
            return true;
        } else if (word.matches("^[a-z][a-zA-Z0-9]*([A-Z][a-zA-Z0-9]*)*$")) {
            return true;
        } else return word.matches("^[a-z][a-zA-Z0-9]*(_[a-z-0-9][a-z-0-9]*)*$");
    }
}
