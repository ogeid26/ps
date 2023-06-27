package org.austral.edu;

import org.austral.edu.Errors.AssignationError;
import org.austral.edu.Errors.EmptyContentError;
import org.austral.edu.Errors.ValueNotFoundError;
import org.austral.edu.InnerInterpreters.*;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PrintInterpreter_2 implements InterpreterStrategy_2{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter(), new BinaryInterpreter(), new ReaderInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Print");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws AssignationError, ValueNotFoundError, EmptyContentError {
        Node n = node.children.get(0);
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(n)){
                try{
                    String message = strategy.interpret(n,types,values);
                    result.savePrintElement(message);
                    break;
                }catch (Error e){
                    System.out.println(e.getMessage());
                    throw new AssignationError();
                }
            }
        }
    }
}