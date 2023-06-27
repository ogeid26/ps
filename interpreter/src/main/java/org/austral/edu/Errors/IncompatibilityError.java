package org.austral.edu.Errors;

public class IncompatibilityError extends Exception {
    public IncompatibilityError() {
        super("Incompatible type and value");
    }
}
