package org.austral.edu.Nodes;

import java.util.ArrayList;

public class ValueNode extends Node{
    public ValueNode(String content) {
        super(content, "Value");
    }

    public ValueNode(String content, ArrayList<Node> children) {
        super(content, "Value", children);
    }
}
