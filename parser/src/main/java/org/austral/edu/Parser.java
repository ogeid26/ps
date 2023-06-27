package org.austral.edu;


import org.austral.edu.Nodes.Node;

import java.util.ArrayList;

public class Parser {

    public AbstractSyntaxTree parse(ArrayList<Token> tokens) {
        //Step 0: Initialize variables
        AbstractSyntaxTree ast = new AbstractSyntaxTree();
        ArrayList<SentenceParser> parsers = new ArrayList<>();
        parsers.add(new AssignationParser());
        parsers.add(new DeclarationParser());

        //Step 1: Divide the general list in smaller lists according to the SEMICOLON.
        ArrayList<ArrayList<Token>> body = new ArrayList<>();
        ArrayList<Token> temp = new ArrayList<>();
        for (Token token : tokens) {
            temp.add(token);
            if (token.tokenType.equals(TokenType.SEMICOLON)) {
                body.add(temp);
                temp = new ArrayList<>();
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
