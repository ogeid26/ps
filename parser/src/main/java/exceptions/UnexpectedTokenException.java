package exceptions;

import org.austral.edu.Token;

public class UnexpectedTokenException extends Throwable {

    public UnexpectedTokenException(Token token) {
        super("Unexpected token: " + token.tokenType.getName());
    }
}
