package org.austral.edu;

public class TokenizerV1 implements Tokenizer {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        for (TokenTypeV1 type: TokenTypeV1.values()) {
            if (type.getName().equals(""))
                continue;
            if (type.getName().equals(currentString))
               return new Token(type, currentString);
            if (currentString.matches("^[0-9]+.?[0-9]*"))
                return new Token(TokenTypeV1.NUMBER, currentString);
            if (currentString.contains("\"") || currentString.contains("'"))
                return new Token(TokenTypeV1.STRING, currentString.substring(1,currentString.length()-1));
        }
        return new Token(TokenTypeV1.IDENTIFIER, currentString);
    }
}
