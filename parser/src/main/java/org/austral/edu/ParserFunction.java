package org.austral.edu;

import java.util.ArrayList;

public class ParserFunction implements SentenceParser {
    @Override
    public boolean validate(ArrayList<Token> tokens) {
        return false;
    }

    @Override
    public Node parse(ArrayList<Token> tokens) {
        return null;
    }
}
