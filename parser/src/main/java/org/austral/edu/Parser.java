package org.austral.edu;


import java.util.ArrayList;

public class Parser {

    public int parse(ArrayList<Token> tokens) {
        //Step 0: Initialize variables
        AbstractSyntaxTree output = new AbstractSyntaxTree();
        ArrayList<ParserStrategy> parsers = new ArrayList<>();
        parsers.add(new ParserFunction());
        parsers.add(new ParserIdentifier());
        parsers.add(new ParserKeyword());

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
            ArrayList<Node> sentence = new ArrayList<>();
            for (ParserStrategy parser: parsers) {
                if (parser.validate(list)) {
                    sentence.add(parser.parse(list));
                }
            }
            output.addSentence("Sentence", sentence);
        }

        return body.size();
    }
}
