package org.austral.edu;

import java.util.ArrayList;

public class Node {
    String content;
    ArrayList<Node> children;

    public Node(String content) {
        this.content = content;
        this.children = new ArrayList<>();
    }

    public Node(String content, ArrayList<Node> children) {
        this.content = content;
        this.children = children;
    }
}