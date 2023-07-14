package org.austral.edu.Nodes;

import ast.Node;

import java.util.ArrayList;

public class ValueNumberNode extends ValueNode {

    public ValueNumberNode(String value) {
        super(value, "number");
    }
}
