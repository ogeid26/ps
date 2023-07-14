package ast;

import java.util.List;

public class PrintNode extends Node {

    public PrintNode(ExpressionNode expression) {
        super("","Print", List.of(expression));
    }
}
