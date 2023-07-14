package org.austral.edu;

import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FunctionInterpreter_2 implements InterpreterStrategy_2{
    ArrayList<InterpreterStrategy_2> strategies = new ArrayList<>(Arrays.asList(new PrintInterpreter_2(), new IfInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.getType().equals("Function");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws InterpretException, IncompatibilityException, AssignationException, NotDefinedException, ConstantVariableException, IllogicalConditionalException {
        assert node.children != null;
        Node n = node.children.get(0);
        for (InterpreterStrategy_2 strategy: strategies) {
            if (strategy.validate(n)){
                strategy.interpret(n,types,values, constants, result);
                break;
            }
        }
    }
}