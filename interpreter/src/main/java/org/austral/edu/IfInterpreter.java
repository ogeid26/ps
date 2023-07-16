package org.austral.edu;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Nodes.BinaryNode;
import org.austral.edu.Nodes.IfNode;
import ast.Node;
import org.austral.edu.Nodes.ValueBooleanNode;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class IfInterpreter implements InterpreterStrategy_2{
    @Override
    public boolean validate(Node node) {
        return node.getType().equals("If");
    }

    @Override
    public void interpret(Node node, HashMap<String, String> types, HashMap<String, String> values, ArrayList<String> constants, Result result, Input input) throws InterpretException, IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        IfNode ifNode = (IfNode) node;
        Interpreter_2 interpreter = new Interpreter_2(types,values,constants, result, input);
        boolean condition;

        if(ifNode.getContent().equals("true") || ifNode.getContent().equals("false")){
            condition = ifNode.getContent().equals("true");
        }else if(values.containsKey(ifNode.getContent()) && types.get(ifNode.getContent()).equals("boolean")){
            condition = values.get(ifNode.getContent()).equals("true");
        }else throw new IllogicalConditionalException();

        if (condition){
            interpreter.interpret(ifNode.getTrueTree());
        }else{
            interpreter.interpret(ifNode.getFalseTree());
        }
    }
}
