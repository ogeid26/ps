package org.austral.edu;

import ast.*;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Results.Result;
import java.util.HashMap;

public class IdentifierInterpreterV1 implements InterpreterStrategyV1 {

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.getType().equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws IncompatibilityException, NotDefinedException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        AssignNode assignNode = (AssignNode) node;
        IdentifierNode nameNode = assignNode.getIdentifierNode();

        if(!types.containsKey(nameNode.getContent())){
            throw new NotDefinedException();
        }else {
            Node valueNode = assignNode.getExpressionNode().solve(values, types);
            if (valueNode.getType().equals(types.get(nameNode.getContent()))) {
                values.put(nameNode.getContent(), valueNode.getContent());
            } else {
                throw new IncompatibilityException();
            }
        }
    }
}
