package org.austral.edu.Nodes;

import ast.Node;

import java.util.List;

public class ReadInputNode extends Node {
    public ReadInputNode(String content) {
        super(content, "ReadInput", List.of());
    }
}
