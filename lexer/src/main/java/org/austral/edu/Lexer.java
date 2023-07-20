package org.austral.edu;
import exceptions.*;

import java.util.List;

public interface Lexer {
    // El lexer solo lexea
    List<Token> lex(InputProvider input) throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, ExpressionDetectedException, UnknownTokenException, WrongCaseException;
}