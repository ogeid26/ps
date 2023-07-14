package exceptions;

public class DividedByZeroException extends Throwable {

    public DividedByZeroException() {
        super("Can't divided by Zero.");
    }
}
