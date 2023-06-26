package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FunctionInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Function");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError {
        Node n = node.children.get(0);
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                String message = strategy.interpret(n,types,values);
                if (message.equals("Error")){
                    throw new AssignationError();
                }else {
                    System.out.println(message);
                }
            }
        }
    }
}
