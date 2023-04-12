package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeNode extends Node{
    public TypeNode(String content) {
        super(content, "Type");
    }

    public TypeNode(String content, ArrayList<Node> children) {
        super(content, "Type", children);
    }
}
