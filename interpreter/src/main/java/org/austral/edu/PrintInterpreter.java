package org.austral.edu;

import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.MathInterpreter;
import org.austral.edu.InnerInterpreters.NameInterpreter;
import org.austral.edu.InnerInterpreters.SubInterpreterStrategy;
import org.austral.edu.InnerInterpreters.ValueInterpreter;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrintInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.getType().equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException {
        assert node.children != null;
        Node n = node.children.get(0);
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                try{
                    Node message = strategy.interpret(n,types,values);
                    if (values.containsKey(message.getContent())){
                        result.savePrintElement(values.get(message.getContent()));
                    }else{
                        result.savePrintElement(message.getContent());
                    }
                    break;
                }catch (InterpretException e){
                    System.out.println(e.getMessage());
                    throw new AssignationException();
                }
            }
        }
    }
}
