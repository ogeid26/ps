package exceptions;

public class IncompatibleOperationException extends Throwable {

    public IncompatibleOperationException(String operation, String left, String right) {
        super("Can't do " + operation + " with " + left + " and " + right + ".");

    }
}
