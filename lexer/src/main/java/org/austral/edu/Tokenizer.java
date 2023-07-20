package org.austral.edu;

import exceptions.ExpressionDetectedException;
import exceptions.UnknownTokenException;
import exceptions.WrongCaseException;

public interface Tokenizer {
    Token tokenize(String currentString, int from, int fromCol, int col, int row) throws ExpressionDetectedException, ExpressionDetectedException, ExpressionDetectedException, UnknownTokenException, WrongCaseException;

}
