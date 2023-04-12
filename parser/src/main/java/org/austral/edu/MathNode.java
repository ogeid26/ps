package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class MathNode extends Node{

    public MathNode(String content) {
        super(content, "Math");
    }

    public MathNode(String content, ArrayList<Node> children) {
        super(content, "Math", children);
    }

    Node getLeft(){
        return children.get(0);
    }

    Node getRight(){
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
}
