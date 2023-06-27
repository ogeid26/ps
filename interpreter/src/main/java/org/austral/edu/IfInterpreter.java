package org.austral.edu;

import org.austral.edu.Nodes.BinaryNode;
import org.austral.edu.Nodes.IfNode;
import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class IfInterpreter implements InterpreterStrategy_2{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("If");
    }

    @Override
    public void interpret(Node node, HashMap<String, String> types, HashMap<String, String> values, ArrayList<String> constants) throws AssignationError, IncompatibilityError, NotDefinedError {
        IfNode ifNode = (IfNode) node;
        Interpreter_2 interpreter = new Interpreter_2(types,values,constants);
        Node condition = ifNode.getCondition();
        if (condition instanceof BinaryNode){
            if (condition.content.equals("TRUE")){
                interpreter.interpret(ifNode.getTrueTree());
            }else{
                interpreter.interpret(ifNode.getFalseTree());
            }
        }else  if(values.containsKey(condition.content) && types.get(condition.content).equals("Boolean")){
            if (values.get(condition.content).equals("TRUE")){
                interpreter.interpret(ifNode.getTrueTree());
            }else{
                interpreter.interpret(ifNode.getFalseTree());
            }
        }else{
            throw new RuntimeException("Illogical condition");
        }
    }
}
