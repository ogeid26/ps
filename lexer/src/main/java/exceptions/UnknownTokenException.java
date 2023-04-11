package exceptions;

public class UnknownTokenException extends Throwable{
    public UnknownTokenException(String str, int col, int row) {
        super("Could not recognize token: " +str +  ", at line" + col + ", row" + row);
    }
}
