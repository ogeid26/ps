package org.austral.edu;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FunctionInterpreter implements InterpreterStrategyV2 {
    ArrayList<InterpreterStrategyV2> strategies = new ArrayList<>(Arrays.asList(new PrintInterpreterV2(), new IfInterpreter()));

    @Override
    public boolean validate(Node node) {
        return (isPrint(node) || isAnIf(node));
    }

    private boolean isAnIf(Node node) {
        return node.getType().equals("If");
    }

    private boolean isPrint(Node node) {
        return node.getType().equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input) throws InterpretException, IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException, UndefinedConstException {
        for (InterpreterStrategyV2 strategy: strategies) {
            if (strategy.validate(node)){
                strategy.interpret(node,types,values, constants, result, input);
                break;
            }
        }
    }
}