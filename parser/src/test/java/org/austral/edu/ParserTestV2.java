package org.austral.edu;

import org.austral.edu.Nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTestV2 {

    @Test
    public void testParseDeclarationSentence() {
        Parser parser = new ParserV2();

        ArrayList<Token> tokens = new ArrayList<>(List.of(
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "name"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.BOOLEANTYPE, "Boolean"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "lastName"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "Number"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.IDENTIFIER, "lastName"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "Number"),
                new Token(TokenType.ASSIGNATION, "="),
                new Token(TokenType.NUMBERTYPE, "90"),
                new Token(TokenType.SEMICOLON, ";")
        ));

        AbstractSyntaxTree ast = parser.parse(tokens);

        assertEquals(2, ast.root.children.size());
        for (Node node : ast.root.children) {
            assertEquals("Declare", node.type);
        }
    }
}