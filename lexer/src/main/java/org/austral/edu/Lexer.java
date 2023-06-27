package org.austral.edu;

import exceptions.UnclosedStringLiteralException;
import exceptions.UnknownTokenException;

import java.util.ArrayList;

public interface Lexer {
    // El lexer solo lexea
    ArrayList<Token> lex(String input) throws UnclosedStringLiteralException, UnknownTokenException;
}