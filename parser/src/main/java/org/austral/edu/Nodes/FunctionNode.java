package org.austral.edu.Nodes;

import java.util.ArrayList;

public class FunctionNode extends Node{

    public FunctionNode() {
        super("function", "Function");
    }

    public FunctionNode(ArrayList<Node> children) {
        super("function", "Function", children);
    }
}