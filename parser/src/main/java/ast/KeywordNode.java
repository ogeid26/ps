package ast;

import java.util.List;

public class KeywordNode extends Node {

    public KeywordNode(String content) {
        super(content, "Keyword", List.of());
    }
}
