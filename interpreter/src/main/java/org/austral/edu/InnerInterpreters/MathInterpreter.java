package org.austral.edu.InnerInterpreters;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
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
        return node.getType().equals("Math");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws InterpretException {
        ExpressionNode expressionNode = (ExpressionNode) node;
        Object result;
        try {
            result = expressionNode.solve(values, types);
        } catch (Exception | IncompatibleOperationException | DividedByZeroException e) {
            System.out.println(e.getMessage());
            throw new MathException();
        } catch (VariableDoesntExistsException e) {
            throw new RuntimeException(e);
        }
        return new ValueStringNode(result.toString());
    }
}
