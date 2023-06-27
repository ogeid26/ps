package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public interface InterpreterStrategy_2 {
    boolean validate(Node node);

    void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants) throws AssignationError, IncompatibilityError, NotDefinedError, EmptyContentError, ValueNotFoundError, ConstantVariableError, IllogicalConditionalError;
}
