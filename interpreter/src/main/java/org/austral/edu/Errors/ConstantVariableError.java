package org.austral.edu.Errors;

public class ConstantVariableError extends Exception{
    public ConstantVariableError() {
        super("This variable is a constant, you cannot change it");
    }
}
