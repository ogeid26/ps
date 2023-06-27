package org.austral.edu;

import org.austral.edu.Nodes.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DeclareAndAssignParser implements SentenceParser {

    TokenType[][] pattern;

    DeclareAndAssignParser() {
        pattern = new TokenType[][]{
                {TokenType.KEYWORD},
                {TokenType.IDENTIFIER},
                {TokenType.DECLARATION},
                {TokenType.NUMBERTYPE, TokenType.STRINGTYPE},
                {TokenType.ASSIGNATION},
                {TokenType.NUMBER, TokenType.STRING}
        };
    }
    @Override
    public boolean validate(ArrayList<Token> tokens) {
        if (tokens.size() < pattern.length)
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
        children.add(new ValueNode(tokens.get(5).content));
        return new AssignDeclareNode(children);
    }
}
