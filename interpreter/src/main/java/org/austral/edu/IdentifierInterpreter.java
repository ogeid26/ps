package org.austral.edu;

import ast.*;
import ast.ExpressionNode;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.MathInterpreter;
import org.austral.edu.InnerInterpreters.NameInterpreter;
import org.austral.edu.InnerInterpreters.SubInterpreterStrategy;
import org.austral.edu.InnerInterpreters.ValueInterpreter;
import org.austral.edu.Nodes.*;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IdentifierInterpreter implements InterpreterStrategy{

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.getType().equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException, IncompatibilityException, NotDefinedException, ValueNotFoundException, EmptyContentException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            IdentifierNode nameNode = assignNode.getIdentifierNode();

            Node valueNode = assignNode.getExpressionNode().solve(values, types);

            if (valueNode.getType().equals(types.get(nameNode.getContent()))){
                values.put(nameNode.getContent(), valueNode.getContent());
            }else{
                throw new IncompatibilityException();
            }
        }
    }
}
