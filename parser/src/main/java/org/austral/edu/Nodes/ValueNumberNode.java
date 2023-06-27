package org.austral.edu.Nodes;

import java.util.ArrayList;

public class ValueNumberNode extends Node{
    public ValueNumberNode(String content) {
        super(content, "ValueNumber");
    }

    public ValueNumberNode(String content, ArrayList<Node> children) {
        super(content, "ValueNumber", children);
    }
}