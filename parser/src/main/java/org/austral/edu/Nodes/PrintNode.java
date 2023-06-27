package org.austral.edu.Nodes;

import java.util.ArrayList;

public class PrintNode extends Node{
    public PrintNode() {
        super("print", "Print");
    }

    public PrintNode(ArrayList<Node> children) {
        super("print", "Print", children);
    }
}
