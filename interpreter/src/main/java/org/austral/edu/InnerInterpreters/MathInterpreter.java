package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.*;
import org.austral.edu.Helpers.TextHelper;
import org.austral.edu.Nodes.MathNode;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MathInterpreter implements SubInterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(this ,new NameInterpreter(), new ValueInterpreter()));
    TextHelper textHelper = new TextHelper();
    @Override
    public boolean validate(Node node) {
        return node.type.equals("Math");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws InterpretException {
        MathNode mathNode = (MathNode) node;
        Node left = null;
        for (SubInterpreterStrategy strategy : strategies) {
            if (strategy.validate(mathNode.getLeft())) {
                left = strategy.interpret(mathNode.getLeft(), types, values);
                break;
            }
        }
        Node right = null;
        for (SubInterpreterStrategy strategy : strategies) {
            if (strategy.validate(mathNode.getRight())) {
                right = strategy.interpret(mathNode.getRight(), types, values);
                break;
            }
        }
        assert left != null;
        assert right != null;
        Object result;
        try {
            result = mathNode.solve(textHelper.parseString(left.content), textHelper.parseString(right.content));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new MathException();
        }
        if (result instanceof Double || result instanceof Integer) {
            return new ValueNumberNode(result.toString());
        }else{
            return new ValueStringNode(result.toString());
        }
    }
}
