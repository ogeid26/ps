package org.austral.edu;

import ast.AssignNode;
import ast.IdentifierNode;
import ast.Node;
import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.MathInterpreter;
import org.austral.edu.InnerInterpreters.NameInterpreter;
import org.austral.edu.InnerInterpreters.SubInterpreterStrategy;
import org.austral.edu.InnerInterpreters.ValueInterpreter;
import org.austral.edu.Nodes.*;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IdentifierInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.type.equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException, IncompatibilityException, NotDefinedException, ValueNotFoundException, EmptyContentException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            IdentifierNode nameNode = assignNode.getIdentifierNode();
            Node valueName = assignNode.getValueNode();

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueName)){
                    try{
                        String message = strategy.interpret(valueName,types,values);
                        if (isString(types, nameNode, message, valueName)){
                            values.put(nameNode.content, message);
                            break;
                        }else if(isNumber(types, nameNode, message)){
                            values.put(nameNode.content, message);
                            break;
                        }else{
                            throw new IncompatibilityException();
                        }
                    }catch (InterpretException e){
                        System.out.println(e.getMessage());
                        throw new AssignationException();
                    }
                }
            }
        }
    }

    private boolean isString(HashMap<String, String> types, NameNode nameNode, String message, Node valueNode) {
        return types.get(nameNode.content).equals("String") && !isInteger(message) && !(valueNode instanceof BinaryNode);
    }

    private boolean isNumber(HashMap<String, String> types, NameNode nameNode, String message) {
        return types.get(nameNode.content).equals("Number") && isInteger(message);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
