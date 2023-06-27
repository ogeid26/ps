package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.AssignNode;
import org.austral.edu.Nodes.BinaryNode;
import org.austral.edu.Nodes.NameNode;
import org.austral.edu.Nodes.Node;

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
        return node.type.equals("Assignation");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError, NotDefinedError, ValueNotFoundError, EmptyContentError {
        if (types.isEmpty()){
            throw new NotDefinedError();
        }else{
            AssignNode assignNode = (AssignNode) node;
            NameNode nameNode = (NameNode) assignNode.children.get(0);
            Node valueName = assignNode.children.get(1);

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
                            throw new IncompatibilityError();
                        }
                    }catch (Error e){
                        System.out.println(e.getMessage());
                        throw new AssignationError();
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
