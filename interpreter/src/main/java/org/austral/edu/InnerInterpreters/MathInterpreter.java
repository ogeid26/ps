package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.*;
import org.austral.edu.Helpers.TextHelper;
import ast.ExpressionNode;
import ast.Node;
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
        ExpressionNode expressionNode = (ExpressionNode) node;
        Node left = null;
        for (SubInterpreterStrategy strategy : strategies) {
            if (strategy.validate(expressionNode.getLeft())) {
                left = strategy.interpret(expressionNode.getLeft(), types, values);
                break;
            }
        }
        Node right = null;
        for (SubInterpreterStrategy strategy : strategies) {
            if (strategy.validate(expressionNode.getRight())) {
                right = strategy.interpret(expressionNode.getRight(), types, values);
                break;
            }
        }
        assert left != null;
        assert right != null;
        Object result;
        try {
            result = expressionNode.solve(textHelper.parseString(left.content), textHelper.parseString(right.content));
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
