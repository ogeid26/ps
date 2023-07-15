package ast;

import java.util.List;

public class AssignReaderNode extends Node {

    public AssignReaderNode(IdentifierNode id, ReaderNode read) {
        super("", "DeclareReader", List.of(id, read));
    }

    public IdentifierNode getIdentifierNode(){
        return (IdentifierNode) children.get(0);
    }

    public ReaderNode getReaderNode() {
        return (ReaderNode) children.get(1);
    }
}
