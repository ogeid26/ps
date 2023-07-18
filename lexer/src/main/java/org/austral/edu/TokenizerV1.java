package org.austral.edu;

import java.util.ArrayList;

public class TokenizerV1 implements Tokenizer {
    boolean active;
    ArrayList<TokenType> tokens = new ArrayList<>();

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token result = null;
        TokenTypeV1 resultType = null;
        for (TokenTypeV1 type: TokenTypeV1.values()) {
            if (type.getName().equals("")) {
                continue;
            }
            if (type.getName().equals(currentString)){
                result = new Token(type, currentString);
                resultType = type;
                if (type == TokenTypeV1.PRINTLN){
                    active = true;
                }
                break;
            }
            if (currentString.matches("^[0-9]+.?[0-9]*")) {
                result = new Token(TokenTypeV1.NUMBER, currentString);
                resultType = TokenTypeV1.NUMBER;
                break;
            }
            if (currentString.contains("\"") || currentString.contains("'")) {
                result = new Token(TokenTypeV1.STRING, currentString);
                resultType = TokenTypeV1.STRING;
                break;
            }
        }
        if (result == null){
            if (checkCase(currentString)) {
                result = new Token(TokenTypeV1.IDENTIFIER, currentString);
                resultType = TokenTypeV1.IDENTIFIER;
            } else {
                throw new RuntimeException("Case Error");
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
        return result;
    }

    private boolean checkCase(String word) {
        if (word.matches("^[a-z][a-zA-Z0-9]*$")) {
            return true;
        } else if (word.matches("^[a-z][a-zA-Z0-9]*([A-Z][a-zA-Z0-9]*)*$")) {
            return true;
        } else return word.matches("^[a-z][a-zA-Z0-9]*(_[a-z-0-9][a-z-0-9]*)*$");
    }
}
