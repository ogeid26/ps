package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.EmptyContentException;
import org.austral.edu.Helpers.TextHelper;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

import java.util.HashMap;
import java.util.Objects;

public class ValueInterpreter implements SubInterpreterStrategy{
    TextHelper textHelper = new TextHelper();
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Value");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentException {
        if (Objects.equals(node.content, "")){
            throw new EmptyContentException();
        }else {
            Object result = textHelper.parseString(node.content);
            if (result instanceof Double || result instanceof Integer) {
                return new ValueNumberNode(result.toString());
            }else{
                return new ValueStringNode(result.toString());
            }
        }
    }
}
