package org.austral.edu.Errors;

public class ValueNotFoundError extends Exception {

    public ValueNotFoundError() {
        super("this variable has no value");
    }
}
