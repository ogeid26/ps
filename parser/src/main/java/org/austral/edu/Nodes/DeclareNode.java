package org.austral.edu.Nodes;

import java.util.ArrayList;

public class DeclareNode extends Node{
    public DeclareNode() {
        super(" ", "Declare");
    }

    public DeclareNode(ArrayList<Node> children) {
        super(" ", "Declare", children);
    }

    public TypeNode getTypeNode(){
        return (TypeNode) children.get(0);
    }

    public NameNode getNameNode(){
        return (NameNode) children.get(1);
    }
}
