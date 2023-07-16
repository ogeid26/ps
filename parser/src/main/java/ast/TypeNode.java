package ast;

import java.util.List;

public class TypeNode extends Node {

    public TypeNode(String content) {
        super(content, "Type", List.of());
    }
}
