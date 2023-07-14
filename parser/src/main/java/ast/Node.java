package ast;

import java.util.List;

public abstract class Node {

    protected final String content;
    protected final String type;
    public final List<Node> children;

    public Node(String content, String type) {
        this.content = content;
        this.type = type;
        this.children = null;
    }

    public Node(String content, String type, List<Node> children) {
        this.content = content;
        this.type = type;
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
