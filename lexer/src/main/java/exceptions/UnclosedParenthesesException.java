package exceptions;

public class UnclosedParenthesesException extends Throwable{
    public UnclosedParenthesesException(int fromCol, int row) {
        super("Unclosed parenthesis at position: " + fromCol + ", line: " + (row + 1));

}
}
