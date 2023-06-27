package org.austral.edu.Nodes;

import java.util.ArrayList;

public class AssignDeclareNode  extends Node{
    public AssignDeclareNode() {
        super(" = ", "AssignDeclare");
    }

    public AssignDeclareNode(ArrayList<Node> children) {
        super(" = ", "AssignDeclare", children);
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }
}
