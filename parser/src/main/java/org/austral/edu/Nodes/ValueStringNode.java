package org.austral.edu.Nodes;

import java.util.ArrayList;

public class ValueStringNode extends Node{
    public ValueStringNode(String content) {
        super(content, "Value");
    }

    public ValueStringNode(String content, ArrayList<Node> children) {
        super(content, "Value", children);
    }
}