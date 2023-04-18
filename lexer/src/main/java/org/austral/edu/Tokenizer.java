package org.austral.edu;

import exceptions.UnknownTokenException;

public interface Tokenizer {
    Token tokenize(String currentString, int from, int fromCol, int col, int row) throws UnknownTokenException;

}
