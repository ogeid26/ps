package org.austral.edu.Exceptions;

public class EmptyContentException extends InterpretException {

    public EmptyContentException() {
        super("You cannot set an empty content");
    }
}
