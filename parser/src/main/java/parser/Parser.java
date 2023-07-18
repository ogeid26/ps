package parser;


import ast.AbstractSyntaxTree;
import ast.Node;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;
import parser.sentenceParser.AssignationParser;
import parser.sentenceParser.DeclarationParser;
import parser.sentenceParser.SentenceParser;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {

    public AbstractSyntaxTree parse(List<Token> tokens) throws UnexpectedTokenException, ExpectedTokenException {
        List<List<Token>> sentences = splitBySentences(tokens);
        AbstractSyntaxTree ast = new AbstractSyntaxTree();
        for (List<Token> sentence: sentences) {
            ast.addNode(parseSentence(sentence));
        }

        return ast;
    }

    protected List<List<Token>> splitBySentences(List<Token> list) {
        List<List<Token>> subLists = new ArrayList<>();
        List<Token> subList = new ArrayList<>();
        for (Token token : list) {
            subList.add(token);
            if (token.tokenType.equals(TokenTypeV1.SEMICOLON)) {
                subLists.add(subList);
                subList = new ArrayList<>();
            }
        }
        if (!subList.isEmpty()) subLists.add(subList);
        return subLists;
    }

    protected abstract Node parseSentence(List<Token> tokens) throws UnexpectedTokenException, ExpectedTokenException;
}
