package org.austral.edu.Nodes;

import java.util.ArrayList;

public class AssignNode extends Node{
    public AssignNode() {
        super(" = ", "Assign");
    }

    public AssignNode(ArrayList<Node> children) {
        super(" = ", "Assign", children);
    }
}
