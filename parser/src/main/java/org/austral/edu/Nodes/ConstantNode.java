package org.austral.edu.Nodes;

import ast.DeclareNode;
import ast.Node;

import java.util.ArrayList;

public class ConstantNode extends Node {

    public ConstantNode(ArrayList<Node> children) {
        super(" = ", "Constant", children);
    }

    public DeclareNode getDeclareNode(){
        return (DeclareNode) children.get(0);
    }
}
