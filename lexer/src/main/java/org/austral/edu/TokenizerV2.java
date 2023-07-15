package org.austral.edu;

public class TokenizerV2 extends TokenizerV1 {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (token.tokenType.equals(TokenTypeV1.IDENTIFIER)) {
            for (TokenTypeV2 type: TokenTypeV2.values()) {
                if (type.getName().equals("")) {
                    continue;
                }
                if (type.getName().equals(currentString)){
                    return new Token(type, currentString);
                }
                if (currentString.equals("true") || currentString.equals("false")) {
                    return new Token(TokenTypeV2.BOOLEAN, currentString);
                }
            }
        }
        return token;
    }
}
