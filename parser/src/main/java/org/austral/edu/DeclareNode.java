package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class DeclareNode extends Node{
    public DeclareNode(String content) {
        super(content, "Declare");
    }

    public DeclareNode(String content, ArrayList<Node> children) {
        super(content, "Declare", children);
    }

    public TypeNode getTypeNode(){
        return (TypeNode) children.get(0);
    }

    public NameNode getNameNode(){
        return (NameNode) children.get(1);
    }
}
