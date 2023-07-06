package org.austral.edu;
import exceptions.UnclosedStringLiteralException;
import exceptions.UnknownTokenException;

import java.util.List;

public interface Lexer {
    // El lexer solo lexea
    List<Token> lex(InputProvider input) throws  UnclosedStringLiteralException;
}