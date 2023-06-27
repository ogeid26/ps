package org.austral.edu.InnerInterpreters;

import org.austral.edu.Errors.AssignationError;
import org.austral.edu.Errors.EmptyContentError;
import org.austral.edu.Errors.ValueNotFoundError;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;

public interface SubInterpreterStrategy {
    boolean validate(Node node);

    String interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, EmptyContentError, ValueNotFoundError;
}
