package exceptions;

import org.austral.edu.Token;
import org.austral.edu.TokenType;

public class ExpectedTokenException extends Throwable {

    public ExpectedTokenException(TokenType tokenType) {
        super("Expected token: " + tokenType.getName());
    }
}
