package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.ValueNotFoundException;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;

public class NameInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Name");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws ValueNotFoundException {
        if (values.containsKey(node.content)){
            return values.get(node.content);
        }else{
            throw new ValueNotFoundException();
        }
    }
}
