package ast;

import org.austral.edu.Nodes.ValueNode;

public class LiteralNode extends ExpressionNode {

    private final ValueNode value;

    public LiteralNode(ValueNode value) {
        super("Literal");
        this.value = value;
    }

    public Node solve() {
        return value;
    }
}
