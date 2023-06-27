package org.austral.edu.Nodes;

import java.util.ArrayList;

public class MathNode extends Node{

    public MathNode(String content) {
        super(content, "Math");
    }

    public MathNode(String content, ArrayList<Node> children) {
        super(content, "Math", children);
    }

    public Node getLeft(){
        return children.get(0);
    }

    public Node getRight(){
        return children.get(1);
    }

    public int solve(int left, int right) {
        int result = 0;
        switch (content){
            case "+" ->{
                result = left + right;
            }
            case "-" ->{
                result = left - right;
            }
            case "*" ->{
                result = left * right;
            }
            case "/" ->{
                result = left / right;
            }
        }
        return result;
    }

    public String solve(String left, String right) {
        String result = null;
        switch (content){
            case "+" ->{
                result = left + right;
            }
            default -> {
                result = "Error";
            }
        }
        return result;
    }
}
