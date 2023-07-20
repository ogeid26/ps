package linter;

import exceptions.ExpressionDetectedException;
import exceptions.UnknownTokenException;
import exceptions.WrongCaseException;
import org.austral.edu.*;

import java.util.ArrayList;

public class TokenizerAnalyzer extends TokenizerV2 {
    boolean active;
    ArrayList<TokenType> tokens = new ArrayList<>();

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) throws ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (token.tokenType == TokenTypeV1.PRINTLN || token.tokenType == TokenTypeV2.READ_INPUT){
            active = true;
        }
        if (active){
            tokens.add(token.tokenType);
            if (tokens.size() == 4){
                active = false;
                if (tokens.get(3) == TokenTypeV1.R_PAR){
                            tokens = new ArrayList<>();
                }else{
                    throw new ExpressionDetectedException();
                }
            }
        }
        return token;
    }
}
