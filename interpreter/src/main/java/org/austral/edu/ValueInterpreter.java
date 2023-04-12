package org.austral.edu;

import java.util.HashMap;

public class ValueInterpreter implements InterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Value");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) {
        return node.content;
    }
}
