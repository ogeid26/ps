package ast;

import java.util.List;

public class AssignDeclareNode extends Node {

    public AssignDeclareNode(DeclareNode declaration, ExpressionNode value) {
        super("", "AssignDeclare", List.of(declaration, value));
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }

    public ExpressionNode getExpressionNode() {
        return (ExpressionNode) children.get(1);
    }
}
