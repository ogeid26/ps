package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.EmptyContentException;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;
import java.util.Objects;

public class BinaryInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Binary");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentException {
        if (Objects.equals(node.content, "")){
            throw new EmptyContentException();
        }else {
            return node.content;
        }
    }
}