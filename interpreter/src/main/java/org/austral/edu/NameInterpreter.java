package org.austral.edu;

import java.util.HashMap;

public class NameInterpreter implements InterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Name");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) {
        return values.get(node.content);
    }
}
