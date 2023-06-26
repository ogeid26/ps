package org.austral.edu;

import java.util.ArrayList;

public interface SentenceParser {

    boolean validate(ArrayList<Token> tokens);

    Node parse(ArrayList<Token> tokens);
}