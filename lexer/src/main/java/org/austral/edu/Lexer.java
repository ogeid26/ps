package org.austral.edu;

import exceptions.UnclosedStringException;
import exceptions.UnknownTokenException;

import java.util.ArrayList;

public interface Lexer {
    // El lexer solo lexea
    ArrayList<Token> lex(String input) throws UnclosedStringException, UnknownTokenException;
}