package org.austral.edu;

import java.util.ArrayList;
import java.util.Arrays;

public class AssignationParser implements SentenceParser {

    TokenType[][] pattern;

    AssignationParser() {
        pattern = new TokenType[][]{
                {TokenType.IDENTIFIER},
                {TokenType.ASSIGNATION},
                {TokenType.NUMBER, TokenType.STRING, TokenType.IDENTIFIER}
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
        children.add(new NameNode(tokens.get(0).content));
        Token target = tokens.get(2);
        children.add(switch (target.tokenType) {
            case NUMBER -> new ValueNumberNode(target.content);
            case STRING -> new ValueStringNode(target.content);
            case IDENTIFIER -> new NameNode(target.content);
            default -> new MathNode("TODO EXPRESSION PARSER", new ArrayList<>());
        });
        return new AssignNode(children);
    }
}
