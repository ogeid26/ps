package org.austral.edu;

import exceptions.ExpressionDetectedException;
import exceptions.UnknownTokenException;
import exceptions.WrongCaseException;

import java.util.ArrayList;

public class TokenizerV2 extends TokenizerV1 {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) throws ExpressionDetectedException, ExpressionDetectedException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (token.tokenType.equals(TokenTypeV1.IDENTIFIER)) {
            for (TokenTypeV2 type: TokenTypeV2.values()) {
                if (type.getName().equals(""))
                    continue;
                if (type.getName().equals(currentString))
                    return new Token(type, currentString);
                if (currentString.equals("true") || currentString.equals("false"))
                    return new Token(TokenTypeV2.BOOLEAN, currentString);
            }
        }
        if (token.tokenType == TokenTypeV1.IDENTIFIER){
            if (!checkCase(token.content)){
                throw new WrongCaseException();
            }
        }
        return token;
    }

    private boolean checkCase(String word) {
        if (word.matches("^[a-z][a-zA-Z0-9]*$")) {
            return true;
        } else if (word.matches("^[a-z][a-zA-Z0-9]*([A-Z][a-zA-Z0-9]*)*$")) {
            return true;
        } else return word.matches("^[a-z][a-zA-Z0-9]*(_[a-z-0-9][a-z-0-9]*)*$");
    }
}
