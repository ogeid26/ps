package org.austral.edu.Nodes;

import ast.Node;

import java.util.ArrayList;

public class ValueStringNode extends ValueNode {

    public ValueStringNode(String value) {
        super(value, "String");
    }
}