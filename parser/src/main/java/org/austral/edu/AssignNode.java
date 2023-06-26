package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignNode extends Node{
    public AssignNode() {
        super("Assign", "Assign");
    }

    public AssignNode(ArrayList<Node> children) {
        super("Assign", "Assign", children);
    }
}
