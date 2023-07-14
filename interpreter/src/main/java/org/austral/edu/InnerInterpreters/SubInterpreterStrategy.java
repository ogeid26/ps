package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.*;
import ast.Node;

import java.util.HashMap;

public interface SubInterpreterStrategy {
    boolean validate(Node node);

    Node interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws InterpretException;
}
