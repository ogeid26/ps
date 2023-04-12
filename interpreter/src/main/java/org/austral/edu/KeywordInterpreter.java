package org.austral.edu;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeywordInterpreter implements InterpreterStrategy{
    ArrayList<InterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));
    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node));
    }

    private boolean isDeclare(Node node) {
        return node.type.equals("Declare");
    }

    @Override
    public String interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node n = assignDeclareNode.children.get(1);

            for (InterpreterStrategy strategy: strategies) {
                if (strategy.validate(n)){
                    values.put(declareNode.getNameNode().content,strategy.interpret(n,types,values));
                }
            }

            if (types.get(declareNode.getNameNode().content).equals("String") && !isInteger(values.get(declareNode.getNameNode().content))){
                return "Success";
            } else if (types.get(declareNode.getNameNode().content).equals("Number") && isInteger(values.get(declareNode.getNameNode().content))) {
                return "Success";
            }else{
                return "Incompatible type and value";
            }
        }else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().content, declareNode.getTypeNode().content);
            return "Success";
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
