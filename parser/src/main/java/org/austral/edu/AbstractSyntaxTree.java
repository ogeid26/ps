package org.austral.edu;

import java.util.ArrayList;

public class AbstractSyntaxTree {

    public Node root;
    
    public AbstractSyntaxTree() {
        root = new RootNode("root");
    }

    public void addSentence(Node node) {
        root.children.add(node);
    }
}