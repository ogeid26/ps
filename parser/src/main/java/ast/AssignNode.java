package ast;

import java.util.List;

public class AssignNode extends Node {

    public AssignNode(IdentifierNode id, ExpressionNode value) {
        super("", "Assign", List.of(id, value));
    }

    public IdentifierNode getIdentifierNode() {
        return (IdentifierNode) children.get(0);
    }

    public ExpressionNode getExpressionNode() {
        return (ExpressionNode) children.get(1);
    }
}
