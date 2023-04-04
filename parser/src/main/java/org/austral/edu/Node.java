package org.austral.edu;

import java.util.ArrayList;

class Node {
    String content;
    ArrayList<Node> children;

    Node(String content) {
        this.content = content;
        this.children = new ArrayList<>();
    }

    Node(String content, ArrayList<Node> children) {
        this.content = content;
        this.children = children;
    }
}