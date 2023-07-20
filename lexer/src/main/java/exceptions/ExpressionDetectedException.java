package exceptions;

public class ExpressionDetectedException extends Exception{

    public ExpressionDetectedException() {
        super("println and readInputs can only have literals in their arguments, not expressions");
    }
}
