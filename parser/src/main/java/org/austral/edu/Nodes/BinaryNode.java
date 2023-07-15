package org.austral.edu.Nodes;

import ast.Node;

import java.util.List;

public abstract class BinaryNode extends Node {
    public BinaryNode(String content) {
        super(content, "Binary", List.of());
    }
}
