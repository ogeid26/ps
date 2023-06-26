package org.austral.edu;

public class NotDefinedError extends Exception {
    public NotDefinedError() {
        super("Your variable has not been defined");
    }
}
