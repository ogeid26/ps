package org.austral.edu.Exceptions;

public class NotDefinedException extends Exception {
    public NotDefinedException() {
        super("Your variable has not been defined");
    }
}
