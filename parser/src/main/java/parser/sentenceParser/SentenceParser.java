package parser.sentenceParser;

import ast.Node;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;

import java.util.List;

public interface SentenceParser {

    boolean validate(List<Token> tokens);

    Node parse(List<Token> tokens) throws UnexpectedTokenException;
}
