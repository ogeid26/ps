package org.austral.edu;

import java.util.ArrayList;

public class FunctionNode extends Node{

    public FunctionNode(String content) {
        super(content, "Function");
    }

    public FunctionNode(String content, ArrayList<Node> children) {
        super(content, "Function", children);
    }
}
