package exceptions;

public class UnexpectedTokenException extends Throwable {

    public UnexpectedTokenException(String token) {
        super("Unexpected token: " + token + ".");
    }
}
