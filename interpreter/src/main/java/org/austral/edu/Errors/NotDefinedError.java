package org.austral.edu.Errors;

public class NotDefinedError extends Exception {
    public NotDefinedError() {
        super("Your variable has not been defined");
    }
}
