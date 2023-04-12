package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IdentifierInterpreter implements InterpreterStrategy{
    ArrayList<InterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.type.equals("Assignation");
    }

    @Override
    public String interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) {
        if (types.isEmpty()){
            return "Your variable has not been defined";
        }else{
            AssignNode assignNode = (AssignNode) node;
            NameNode nameNode = (NameNode) assignNode.children.get(0);
            Node n = assignNode.children.get(1);

            for (InterpreterStrategy strategy: strategies) {
                if (strategy.validate(n)){
                    values.put(nameNode.content,strategy.interpret(n,types,values));
                }
            }

            if (types.get(nameNode.content).equals("String") && !isInteger(values.get(nameNode.content))){
                return "Success";
            } else if (types.get(nameNode.content).equals("Number") && isInteger(values.get(nameNode.content))) {
                return "Success";
            }else{
                return "Incompatible type and value";
            }
        }
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
