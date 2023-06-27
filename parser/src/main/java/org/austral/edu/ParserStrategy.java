package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.ArrayList;

public interface ParserStrategy {

    boolean validate(ArrayList<Token> tokens);

    Node parse(ArrayList<Token> tokens);
}