package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class AssignDeclareNode  extends Node{
    public AssignDeclareNode(String content) {
        super(content, "AssignDeclare");
    }

    public AssignDeclareNode(String content, ArrayList<Node> children) {
        super(content, "AssignDeclare", children);
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }
}
