package org.austral.edu;

import java.util.HashMap;

public interface InterpreterStrategy {
    boolean validate(Node node);

    void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError, NotDefinedError;
}
