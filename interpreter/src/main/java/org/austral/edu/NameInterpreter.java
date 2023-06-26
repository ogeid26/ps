package org.austral.edu;

import java.util.HashMap;
import java.util.Objects;

public class NameInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Name");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) {
        return values.getOrDefault(node.content, "Error");
    }
}
