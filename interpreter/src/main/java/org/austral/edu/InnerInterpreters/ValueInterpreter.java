package org.austral.edu.InnerInterpreters;

import org.austral.edu.Errors.EmptyContentError;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;
import java.util.Objects;

public class ValueInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Value");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentError {
        if (Objects.equals(node.content, "")){
            throw new EmptyContentError();
        }else {
            return node.content;
        }
    }
}
