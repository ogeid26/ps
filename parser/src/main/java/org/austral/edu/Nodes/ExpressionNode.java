package org.austral.edu.Nodes;

import ast.Node;

import java.util.ArrayList;

public class ExpressionNode extends Node {

    public ExpressionNode(String content, ArrayList<Node> children) {
        super(content, "Math", children);
    }

    public Node getLeft(){
        return children.get(0);
    }

    public Node getRight(){
        return children.get(1);
    }

    public Object solve(Object left, Object right) {
        if (left instanceof String || right instanceof String) {
            if ("+".equals(content)) {
                return left.toString() + right.toString();
            } else {
                throw new RuntimeException("It is impossible to realize de operation");
            }
        } else if (left instanceof Double || right instanceof Double) {
            double num1 = ((Number) left).doubleValue();
            double num2 = ((Number) right).doubleValue();
            switch (content) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        throw new RuntimeException("Unable to divide by 0");
                    }
                default:
                    return "Error: Operación no válida";
            }
        } else if (left instanceof Integer && right instanceof Integer) {
            int num1 = (int) left;
            int num2 = (int) right;

            switch (content) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        throw new RuntimeException("Unable to divide by 0");
                    }
                default:
                    return "Error: Operación no válida";
            }
        } else {
            return "Error: Al menos uno de los objetos no es un número";
        }
    }
}
