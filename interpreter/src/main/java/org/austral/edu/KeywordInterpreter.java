package org.austral.edu;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeywordInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));
    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node));
    }

    private boolean isDeclare(Node node) {
        return node.type.equals("Declare");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node n = assignDeclareNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(n)){
                    String message = strategy.interpret(n,types,values);
                    if (message.equals("Error")){
                        throw new AssignationError();
                    }else {
                        values.put(declareNode.getNameNode().content, message);
                    }
                }
            }

            if (types.get(declareNode.getNameNode().content).equals("String") && !isInteger(values.get(declareNode.getNameNode().content))){
            } else if (types.get(declareNode.getNameNode().content).equals("Number") && isInteger(values.get(declareNode.getNameNode().content))) {
            }else{
               throw new IncompatibilityError();
            }
        }else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().content, declareNode.getTypeNode().content);
        }
    }

    private boolean isAssignDeclare(Node node) {
        return node.type.equals("AssignDeclare");
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
