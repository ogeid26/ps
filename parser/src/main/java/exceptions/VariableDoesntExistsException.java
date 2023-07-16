package exceptions;

import org.austral.edu.Token;

public class VariableDoesntExistsException extends Throwable {

    public VariableDoesntExistsException(String name) {
        super("Variable: " + name + " doesn't exists.");
    }
}
