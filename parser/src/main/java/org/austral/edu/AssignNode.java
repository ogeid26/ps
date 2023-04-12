package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignNode extends Node{
    public AssignNode(String content) {
        super(content, "Assign");
    }

    public AssignNode(String content, ArrayList<Node> children) {
        super(content, "Assign", children);
    }
}
