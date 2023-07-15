package org.austral.edu.Nodes;

import ast.AbstractSyntaxTree;
import ast.Node;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends Node {

    public IfNode(ValueBooleanNode condition, AbstractSyntaxTree trueTree, AbstractSyntaxTree falseTree) {
        super(condition.getContent(), "If", List.of(trueTree, falseTree));
    }

    public AbstractSyntaxTree getFalseTree() {
        return (AbstractSyntaxTree) children.get(0);
    }

    public AbstractSyntaxTree getTrueTree() {
        return (AbstractSyntaxTree) children.get(1);
    }
}
