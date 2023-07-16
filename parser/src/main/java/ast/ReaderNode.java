package ast;

import java.util.List;

public class ReaderNode extends Node {

    public ReaderNode(String message) {
        super(message, "ReadInput", List.of());
    }
}
