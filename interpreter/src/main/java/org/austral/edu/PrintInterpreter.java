package org.austral.edu;

import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.MathInterpreter;
import org.austral.edu.InnerInterpreters.NameInterpreter;
import org.austral.edu.InnerInterpreters.SubInterpreterStrategy;
import org.austral.edu.InnerInterpreters.ValueInterpreter;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrintInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException {
        Node n = node.children.get(0);
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                try{
                    String message = strategy.interpret(n,types,values);
                    result.savePrintElement(message);
                    break;
                }catch (InterpretException e){
                    System.out.println(e.getMessage());
                    throw new AssignationException();
                }
            }
        }
    }
}
