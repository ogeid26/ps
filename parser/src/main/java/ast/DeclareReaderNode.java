package ast;

import java.util.List;

public class DeclareReaderNode extends Node {

    public DeclareReaderNode(DeclareNode declare, ReaderNode read) {
        super("", "DeclareReader", List.of(declare, read));
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }

    public ReaderNode getReaderNode() {
        return (ReaderNode) children.get(1);
    }
}
