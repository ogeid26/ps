package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Results.Result;

import java.util.HashMap;

public interface InterpreterStrategy {
    boolean validate(Node node);

    void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationError, IncompatibilityError, NotDefinedError, ValueNotFoundError, EmptyContentError;
}
