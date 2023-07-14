package org.austral.edu;

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
        return node.getType().equals("Function");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws InterpretException, IncompatibilityException, AssignationException, NotDefinedException {
        assert node.children != null;
        Node n = node.children.get(0);
        for (InterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                strategy.interpret(n,types,values,result);
                break;
            }
        }
    }
}
