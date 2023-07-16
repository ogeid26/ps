package org.austral.edu.Nodes;

import ast.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class ValueNode extends Node {

    public ValueNode(String value, String type) {
        super(value, type, List.of());
    }
}
