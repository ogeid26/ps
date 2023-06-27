package org.austral.edu.Nodes;

import java.util.ArrayList;

public abstract class Node {
    public String content;
    public String type;
    public ArrayList<Node> children;

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