package org.austral.edu;

import org.austral.edu.*;

import java.util.ArrayList;

public class TokenizerAnalyzer extends TokenizerV2 {
    boolean active;
    ArrayList<TokenType> tokens = new ArrayList<>();

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (token.tokenType == TokenTypeV1.PRINTLN || token.tokenType == TokenTypeV2.READ_INPUT){
            active = true;
        }
        if (active){
            tokens.add(token.tokenType);
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
}
