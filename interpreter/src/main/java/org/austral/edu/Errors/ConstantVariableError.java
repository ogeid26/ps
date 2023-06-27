package org.austral.edu.Errors;

public class ConstantVariableError extends Exception{
    public ConstantVariableError() {
        super("This variable is a constant, you can´t change it");
    }
}
