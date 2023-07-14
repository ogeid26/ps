package org.austral.edu.Nodes;

import ast.AbstractSyntaxTree;
import ast.Node;

import java.util.ArrayList;

public class IfNode extends Node {
    Node condition;
    ArrayList<AbstractSyntaxTree> trueTree;
    ArrayList<AbstractSyntaxTree> falseTree;

    public IfNode(Node condition, ArrayList<AbstractSyntaxTree> trueTree, ArrayList<AbstractSyntaxTree> falseTree) {
        super("if", "If");
        this.condition = condition;
        this.trueTree = trueTree;
        this.falseTree = falseTree;
    }

    public Node getCondition() {
        return condition;
    }

    public ArrayList<AbstractSyntaxTree> getFalseTree() {
        return falseTree;
    }

    public ArrayList<AbstractSyntaxTree> getTrueTree() {
        return trueTree;
    }
}
