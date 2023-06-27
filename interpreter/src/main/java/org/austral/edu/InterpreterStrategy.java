package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;

public interface InterpreterStrategy {
    boolean validate(Node node);

    void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError, NotDefinedError, ValueNotFoundError, EmptyContentError;
}
