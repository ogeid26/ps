package org.austral.edu;

public class IncompatibilityError extends Exception {
    public IncompatibilityError() {
        super("Incompatible type and value");
    }
}
