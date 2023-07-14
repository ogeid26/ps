package ast;

import java.util.List;

public class DeclareNode extends Node {

    public DeclareNode(TypeNode type, IdentifierNode id, KeywordNode keyword) {
        super("", "Declare", List.of(type, id, keyword));
    }

    public TypeNode getTypeNode(){
        return (TypeNode) children.get(0);
    }

    public IdentifierNode getNameNode(){
        return (IdentifierNode) children.get(1);
    }

    public KeywordNode getKeywordNode() {
        return (KeywordNode) children.get(2);
    }
}
