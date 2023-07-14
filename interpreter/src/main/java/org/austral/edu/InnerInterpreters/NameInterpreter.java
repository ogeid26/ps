package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.ValueNotFoundException;
import org.austral.edu.Helpers.TextHelper;
import ast.Node;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

import java.util.HashMap;

public class NameInterpreter implements SubInterpreterStrategy{
    TextHelper textHelper = new TextHelper();
    @Override
    public boolean validate(Node node) {
        return node.getType().equals("Name");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws ValueNotFoundException {
        if (values.containsKey(node.getContent())){
            Object result = textHelper.parseString(node.getContent());
            if (result instanceof Double || result instanceof Integer) {
                return new ValueNumberNode(result.toString());
            }else{
                return new ValueStringNode(result.toString());
            }
        }else{
            throw new ValueNotFoundException();
        }
    }
}
