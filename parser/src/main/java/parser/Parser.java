package parser;


import ast.AbstractSyntaxTree;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import parser.sentenceParser.SentenceParser;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {

    public SentenceParser[] parsers;

    Parser(SentenceParser[] parsers) {
        this.parsers = parsers;
    }

    public AbstractSyntaxTree parse(List<Token> tokens) throws UnexpectedTokenException {
        List<List<Token>> sentences = splitBySentences(tokens);
        AbstractSyntaxTree ast = new AbstractSyntaxTree();
        for (List<Token> sentence: sentences) {
            for (SentenceParser parser: parsers) {
                if (parser.validate(sentence)) {
                    ast.addNode(parser.parse(sentence));
                }
            }
        }

        return ast;
    }

    private static List<List<Token>> splitBySentences(List<Token> list) {
        List<List<Token>> subLists = new ArrayList<>();
        List<Token> subList = new ArrayList<>();
        for (Token token : list) {
            subList.add(token);
            if (token.tokenType.equals(TokenType.SEMICOLON)) {
                subLists.add(subList);
                subList = new ArrayList<>();
            }
        }
        return subLists;
    }
}
