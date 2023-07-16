package ast;

import java.util.List;

public class IdentifierNode extends Node {

    public IdentifierNode(String content) {
        super(content, "Identifier", List.of());
    }
}
