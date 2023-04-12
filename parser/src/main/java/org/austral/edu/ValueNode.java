package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class ValueNode extends Node{
    public ValueNode(String content) {
        super(content, "Value");
    }

    public ValueNode(String content, ArrayList<Node> children) {
        super(content, "Value", children);
    }
}
