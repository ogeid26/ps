package org.austral.edu;

public class TokenizerBetweenVersion extends TokenizerV1{
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {

        Token token = super.tokenize(currentString, from, fromCol, col, row);

        if (token.tokenType.equals(TokenTypeV1.IDENTIFIER)) {
            for (TokenType type : TokenTypeV2.values()) {
                if (type.getName().equals("")) {
                    continue;
                }
                if (type.getName().equals(currentString)) {
                    throw new RuntimeException("Invalid keyword");
                }
            }
        }
        return token;
    }
}
