package org.austral.edu.Errors;

public class EmptyContentError extends Exception {

    public EmptyContentError() {
        super("You cannot set an empty content");
    }
}
