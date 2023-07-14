package org.austral.edu;

import ast.PrintNode;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FunctionInterpreter implements InterpreterStrategy{
    ArrayList<InterpreterStrategy> strategies = new ArrayList<>(List.of(new PrintInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.getType().equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws InterpretException, IncompatibilityException, AssignationException, NotDefinedException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        PrintNode n = (PrintNode) node;
        for (InterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                strategy.interpret(n,types,values,result);
                break;
            }
        }
    }
}
