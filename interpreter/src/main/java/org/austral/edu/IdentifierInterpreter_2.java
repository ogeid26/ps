package org.austral.edu;

import ast.AssignNode;
import ast.IdentifierNode;
import ast.Node;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class IdentifierInterpreter_2 implements InterpreterStrategy_2{

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.getType().equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input) throws IncompatibilityException, NotDefinedException, ConstantVariableException, AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            IdentifierNode nameNode = assignNode.getIdentifierNode();
            if (constants.contains(nameNode.getContent())){
                throw new ConstantVariableException();
            }else {
                Node valueNode = assignNode.getExpressionNode().solve(values, types);

                if (valueNode.getType().equals(types.get(nameNode.getContent()))){
                    values.put(nameNode.getContent(), valueNode.getContent());
                }else{
                    throw new IncompatibilityException();
                }
            }
        }
    }

}
