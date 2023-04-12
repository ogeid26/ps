package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FunctionInterpreter implements InterpreterStrategy{
    ArrayList<InterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Function");
    }

    @Override
    public String interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) {
        Node n = node.children.get(0);
        for (InterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                System.out.println(strategy.interpret(n,types,values));
            }
        }
        return "Success";
    }
}
