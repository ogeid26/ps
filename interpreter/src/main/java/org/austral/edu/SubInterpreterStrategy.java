package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.HashMap;

public interface SubInterpreterStrategy {
    boolean validate(Node node);

    String interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError;
}
