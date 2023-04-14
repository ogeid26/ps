package org.austral.edu;

import java.util.ArrayList;

public class AbstractSyntaxTree {

    public RootNode root;
    
    public AbstractSyntaxTree() {
        root = new RootNode("root");
    }

    public void addSentence(Node node) {
        root.children.add(node);
    }

    public Node getFirstNode(){
        return root.children.get(0);
    }
}