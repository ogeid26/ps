package org.austral.edu;

import ast.AssignDeclareNode;
import ast.DeclareNode;
import ast.Node;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Results.Result;

import java.util.HashMap;

public class KeywordInterpreter implements InterpreterStrategy{

    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node));
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException, IncompatibilityException, ValueNotFoundException, EmptyContentException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
            Node valueNode = assignDeclareNode.getExpressionNode().solve(values, types);

            if (declareNode.getTypeNode().getContent().equals(valueNode.getType())){
                values.put(declareNode.getNameNode().getContent(), valueNode.getContent());
            }else{
                throw new IncompatibilityException();
            }
        }else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
        }
    }

    private boolean isDeclare(Node node) {
        return node.getType().equals("Declare");
    }

    private boolean isAssignDeclare(Node node) {
        return node.getType().equals("AssignDeclare");
    }
}
