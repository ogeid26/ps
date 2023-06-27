package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.ArrayList;

public class ParserKeyword implements ParserStrategy {
    @Override
    public boolean validate(ArrayList<Token> tokens) {
        return false;
    }

    @Override
    public Node parse(ArrayList<Token> tokens) {
        return null;
    }
}
