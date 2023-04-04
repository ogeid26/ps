package org.austral.edu;

import java.util.ArrayList;

public class AbstractSyntaxTree {

    Node root;

    AbstractSyntaxTree() {
        root = new Node("root");
    }

    public void addSentence(String token, ArrayList<Node> children) {
        root.children.add(new Node(token, children));
    }
}