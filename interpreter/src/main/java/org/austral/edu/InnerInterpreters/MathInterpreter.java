package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.*;
import org.austral.edu.Nodes.MathNode;
import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MathInterpreter implements SubInterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(this ,new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return node.type.equals("Math");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws InterpretException {
        MathNode mathNode = (MathNode) node;
        StringBuilder sb = new StringBuilder();
        String left = null;
        String right = null;
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(mathNode.getLeft())){
                left = strategy.interpret(mathNode.getLeft(),types,values);
                break;
            }
        }
        for (SubInterpreterStrategy strategy: strategies) {
            if (strategy.validate(mathNode.getRight())){
                right = strategy.interpret(mathNode.getRight(),types,values);
                break;
            }
        }
        if (isInteger(left)){
            if (isInteger(right)){
                sb.append(mathNode.solve(Integer.parseInt(left), Integer.parseInt(right)));
            }else{
                try {
                    sb.append(mathNode.solve(left, right));
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    throw new MathException();
                }
            }
        }else{
            try{
                sb.append(mathNode.solve(left, right));
            }catch (Exception e){
                System.out.println(e.getMessage());
                throw new MathException();
            }
        }
        return sb.toString();
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
