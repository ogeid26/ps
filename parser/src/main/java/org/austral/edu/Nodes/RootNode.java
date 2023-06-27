package org.austral.edu.Nodes;

import java.util.ArrayList;

public class RootNode extends Node{
    public RootNode(String content) {
        super(content, "root");
    }

    public RootNode(String content, ArrayList<Node> children) {
        super(content, "root", children);
    }
}
