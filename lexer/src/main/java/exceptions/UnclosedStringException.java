package exceptions;
public class UnclosedStringException extends Throwable {
    public UnclosedStringException(int fromCol, int row) {
        super("Unclosed string at " + fromCol + ", line: " + (row + 1));

    }
}
