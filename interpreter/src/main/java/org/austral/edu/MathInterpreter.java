package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MathInterpreter implements InterpreterStrategy{
    ArrayList<InterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(this ,new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Math");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) {
        MathNode mathNode = (MathNode) node;
        StringBuilder sb = new StringBuilder();
        if (isInteger(mathNode.getLeft().content)){
            if (isInteger(mathNode.getRight().content)){
                sb.append(mathNode.solve(Integer.parseInt(mathNode.getLeft().content), Integer.parseInt(mathNode.getRight().content)));
            }else{
                for (InterpreterStrategy strategy: strategies) {
                    if (strategy.validate(mathNode.getRight())){
                        sb.append(mathNode.solve(Integer.parseInt(mathNode.getLeft().content), Integer.parseInt(strategy.interpret(mathNode.getRight(),types,values))));
                    }
                }
            }
        }else{
            if (isInteger(mathNode.getRight().content)){
                for (InterpreterStrategy strategy: strategies) {
                    if (strategy.validate(mathNode.getRight())){
                        sb.append(mathNode.solve(Integer.parseInt(strategy.interpret(mathNode.getLeft(),types,values)), Integer.parseInt(mathNode.getRight().content)));
                    }
                }
            }else{
                int left = 0;
                int right = 0;
                for (InterpreterStrategy strategy: strategies) {
                    if (strategy.validate(mathNode.getLeft())){
                        left = Integer.parseInt(strategy.interpret(mathNode.getLeft(),types,values));
                    }
                }
                for (InterpreterStrategy strategy: strategies) {
                    if (strategy.validate(mathNode.getRight())){
                        right = Integer.parseInt(strategy.interpret(mathNode.getRight(),types,values));
                    }
                }
                sb.append(mathNode.solve(left,right));
            }
        } return sb.toString();
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
