package org.austral.edu.Nodes;

import java.util.ArrayList;

public class TypeNode extends Node{
    public TypeNode(String content) {
        super(content, "Type");
    }

    public TypeNode(String content, ArrayList<Node> children) {
        super(content, "Type", children);
    }
}
