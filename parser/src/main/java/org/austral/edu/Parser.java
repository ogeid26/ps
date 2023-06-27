package org.austral.edu;


import org.austral.edu.Nodes.Node;

import java.util.ArrayList;

public abstract class Parser {

    public SentenceParser[] parsers;

    Parser(SentenceParser[] parsers) {
        this.parsers = parsers;
    }

    public AbstractSyntaxTree parse(ArrayList<Token> tokens) {
        //Step 0: Initialize variables
        AbstractSyntaxTree ast = new AbstractSyntaxTree();

        //Step 1: Divide the general list in smaller lists according to the SEMICOLON.
        ArrayList<ArrayList<Token>> body = new ArrayList<>();
        ArrayList<Token> temp = new ArrayList<>();
        for (Token token : tokens) {
            if (token.tokenType.equals(TokenType.SEMICOLON)) {
                body.add(temp);
                temp = new ArrayList<>();
            } else {
                temp.add(token);
            }
        }

        //Step 2: analyze each sub-list and create each tree.
        for (ArrayList<Token> list: body) {
            for (SentenceParser parser: parsers) {
                if (parser.validate(list)) {
                    ast.addSentence(parser.parse(list));
                }
            }
        }

        return ast;
    }
}
