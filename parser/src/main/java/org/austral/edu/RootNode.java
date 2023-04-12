package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class RootNode extends Node{
    public RootNode(String content) {
        super(content, "root");
    }

    public RootNode(String content, ArrayList<Node> children) {
        super(content, "root", children);
    }
}
