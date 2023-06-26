package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class DeclareNode extends Node{
    public DeclareNode() {
        super("Declare", "Declare");
    }

    public DeclareNode(ArrayList<Node> children) {
        super("Declare", "Declare", children);
    }

    public TypeNode getTypeNode(){
        return (TypeNode) children.get(0);
    }

    public NameNode getNameNode(){
        return (NameNode) children.get(1);
    }
}
