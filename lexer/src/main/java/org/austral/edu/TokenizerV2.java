package org.austral.edu;

import java.util.ArrayList;

public class TokenizerV2 extends TokenizerV1 {

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (token.tokenType.equals(TokenTypeV1.IDENTIFIER)) {
            TokenTypeV2 resultType = null;
            for (TokenTypeV2 type: TokenTypeV2.values()) {
                if (type.getName().equals("")) {
                    continue;
                }
                if (type.getName().equals(currentString)){
                    token = new Token(type, currentString);
                    resultType = type;
                    if (type == TokenTypeV2.READ_INPUT){
                        active = true;
                    }
                    break;
                }
                if (currentString.equals("true") || currentString.equals("false")) {
                    token = new Token(TokenTypeV2.BOOLEAN, currentString);
                    resultType = type;
                    break;
                }
            }
            if (active){
                tokens.add(resultType);
                if (tokens.size() == 4){
                    active = false;
                    if (tokens.get(1) == TokenTypeV1.L_PAR){
                        if (tokens.get(2) == TokenTypeV1.IDENTIFIER || tokens.get(2) == TokenTypeV1.NUMBER || tokens.get(2) == TokenTypeV1.STRING){
                            if (tokens.get(3) == TokenTypeV1.R_PAR){
                                tokens = new ArrayList<>();
                            }else{
                                throw new RuntimeException("Error in function");
                            }
                        }else{
                            throw new RuntimeException("Error in function");
                        }
                    }else{
                        throw new RuntimeException("Error in function");
                    }
                }
            }
            return token;
        }
        return token;
    }
}
