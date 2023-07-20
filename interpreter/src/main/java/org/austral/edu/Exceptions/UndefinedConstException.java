package org.austral.edu.Exceptions;

public class UndefinedConstException extends Exception{
    public UndefinedConstException() {
        super("A constant variable must be initialized");
    }
}
