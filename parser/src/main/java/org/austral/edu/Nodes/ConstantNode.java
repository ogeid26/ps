package org.austral.edu.Nodes;

import java.util.ArrayList;

public class ConstantNode extends Node{

    public ConstantNode() {
        super(" = ", "AssignDeclare");
    }

    public ConstantNode(ArrayList<Node> children) {
        super(" = ", "AssignDeclare", children);
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }
}
