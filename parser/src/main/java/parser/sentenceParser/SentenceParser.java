package parser.sentenceParser;

import ast.Node;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;

import java.util.List;

public interface SentenceParser {

    void validate(List<Token> tokens) throws UnexpectedTokenException, ExpectedTokenException;

    Node parse(List<Token> tokens) throws UnexpectedTokenException, ExpectedTokenException;
}
