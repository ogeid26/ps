package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FunctionInterpreter_2 implements InterpreterStrategy_2{
    ArrayList<InterpreterStrategy_2> strategies = new ArrayList<>(Arrays.asList(new PrintInterpreter_2(), new IfInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Function");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants) throws AssignationError, NotDefinedError, IncompatibilityError {
        Node n = node.children.get(0);
        for (InterpreterStrategy_2 strategy: strategies) {
            if (strategy.validate(n)){
                strategy.interpret(n,types,values, constants);
                break;
            }
        }
    }
}