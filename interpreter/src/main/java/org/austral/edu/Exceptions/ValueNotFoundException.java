package org.austral.edu.Exceptions;

public class ValueNotFoundException extends InterpretException {

    public ValueNotFoundException() {
        super("this variable has no value");
    }
}
