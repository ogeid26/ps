package org.austral.edu.Nodes;

import ast.Node;

import java.util.ArrayList;
import java.util.List;

public class RootNode extends Node {
    public RootNode(String content) {
        super(content, "root", List.of());
    }

    public RootNode(String content, ArrayList<Node> children) {
        super(content, "root", children);
    }

    public List<Node> getChildren() {
        return this.children;
    }
}
