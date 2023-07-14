package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;

import java.util.List;

public abstract class ExpressionNode extends Node {

    public ExpressionNode(String type, ExpressionNode left, ExpressionNode right) {
        super("", type, List.of(left,right));
    }

    public ExpressionNode(String type) {
        super("", type, List.of());
    }

    public ExpressionNode getLeft() {
        return (ExpressionNode) children.get(0);
    }

    public ExpressionNode getRight() {
        return (ExpressionNode) children.get(1);
    }

    public abstract Node solve() throws IncompatibleOperationException, DividedByZeroException;
}
