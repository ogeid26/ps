package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Node {
    String content;
    String type;
    ArrayList<Node> children;

    public Node(String content, String type) {
        this.content = content;
        this.type = type;
        this.children = new ArrayList<>();
    }

    public Node(String content, String type, ArrayList<Node> children) {
        this.content = content;
        this.type = type;
        this.children = children;
    }
}