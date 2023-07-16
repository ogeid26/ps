package exceptions;

public class UnclosedBracesException extends Throwable{

    public  UnclosedBracesException(int fromCol, int row) {
        super("Unclosed string literal at position: " + fromCol + ", line: " + (row + 1));

    }
}
