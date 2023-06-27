package org.austral.edu.Exceptions;

public class ConstantVariableException extends Exception{
    public ConstantVariableException() {
        super("This variable is a constant, you cannot change it");
    }
}
