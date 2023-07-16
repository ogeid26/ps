package org.austral.edu;

import ast.PrintNode;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class PrintInterpreter_2 implements InterpreterStrategy_2{

    @Override
    public boolean validate(Node node) {
        return node.getType().equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input) throws AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        Node valueNode = ((PrintNode) node).getExpressionNode().solve(values, types);
        result.savePrintElement(valueNode.getContent());
    }
}