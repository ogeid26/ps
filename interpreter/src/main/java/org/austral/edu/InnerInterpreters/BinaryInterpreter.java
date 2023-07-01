package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.EmptyContentException;
import org.austral.edu.Nodes.FalseNode;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Nodes.TrueNode;

import java.util.HashMap;
import java.util.Objects;

public class BinaryInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Binary");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentException {
        if (Objects.equals(node.content, "")){
            throw new EmptyContentException();
        }else {
            if (node.content.equals("FALSE")){
                return new FalseNode();
            }else{
                return new TrueNode();
            }
        }
    }
}