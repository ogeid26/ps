package org.austral.edu.Nodes;

import java.util.ArrayList;

public class NameNode extends Node{
    public NameNode(String content) {
        super(content, "Name");
    }

    public NameNode(String content, ArrayList<Node> children) {
        super(content, "Name", children);
    }
}
