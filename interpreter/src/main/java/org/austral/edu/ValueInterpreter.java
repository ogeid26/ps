package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.HashMap;
import java.util.Objects;

public class ValueInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Value");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) {
        if (Objects.equals(node.content, "")){
            return "Error";
        }else {
            return node.content;
        }
    }
}
