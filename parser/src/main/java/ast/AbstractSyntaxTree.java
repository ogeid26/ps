package ast;

import java.util.ArrayList;
import java.util.List;

public class AbstractSyntaxTree extends Node {

    public AbstractSyntaxTree() {
        super("", "Root", new ArrayList<>());
    }

    public void addNode(Node node) {
        getChildren().add(node);
    }

    public List<Node> getChildren() {
        return this.children;
    }
}
