package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;

public class DeclarationParser implements SentenceParser {

    TokenType[][] pattern;

    DeclarationParser() {
        pattern = new TokenType[][]{
                {TokenType.KEYWORD},
                {TokenType.IDENTIFIER},
                {TokenType.DECLARATION},
                {TokenType.NUMBERTYPE, TokenType.STRINGTYPE}
        };
    }
    @Override
    public boolean validate(ArrayList<Token> tokens) {
        if (tokens.size() != pattern.length)
            return false;
        for (int i = 0; i < pattern.length; i++) {
            if (!Arrays.asList(pattern[i]).contains(tokens.get(i).tokenType))
                return false;
        }
        return true;
    }

    @Override
    public Node parse(ArrayList<Token> tokens) {
        ArrayList<Node> children = new ArrayList<>();
        children.add(new TypeNode(tokens.get(3).content));
        children.add(new NameNode(tokens.get(1).content));
        return new DeclareNode(children);
    }
}
