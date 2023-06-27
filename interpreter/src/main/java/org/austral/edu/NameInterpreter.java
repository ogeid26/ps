package org.austral.edu;

import org.austral.edu.Errors.ValueNotFoundError;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;

public class NameInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Name");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws ValueNotFoundError {
        if (values.containsKey(node.content)){
            return values.get(node.content);
        }else{
            throw new ValueNotFoundError();
        }
    }
}
