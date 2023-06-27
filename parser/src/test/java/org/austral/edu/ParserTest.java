package org.austral.edu;

import org.austral.edu.Nodes.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseDeclarationSentence() {
        Parser parser = new ParserV1();

        ArrayList<Token> tokens = new ArrayList<>(List.of(
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "name"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "String"),
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
        for(Node node: ast.root.children) {
            assertEquals("Declare", node.type);
        }
    }

    @Test
    public void testParseDeclarationAndAssignationSentences() {
        Parser parser = new ParserV1();

        ArrayList<Token> tokens = new ArrayList<>(List.of(
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "name"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "String"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "lastName"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "Number"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.IDENTIFIER, "lastName"),
                new Token(TokenType.ASSIGNATION, "="),
                new Token(TokenType.NUMBER, "90"),
                new Token(TokenType.SEMICOLON, ";")
        ));

        AbstractSyntaxTree ast = parser.parse(tokens);

        assertEquals(3, ast.root.children.size());
        String[] typeNodes = new String[]{"Declare", "Declare", "Assign"};
        for (int i = 0; i < typeNodes.length; i++) {
            assertEquals(typeNodes[i], ast.root.children.get(i).type);
        }
    }

    @Test
    public void testParseDeclareAndAssignSentence() {
        Parser parser = new ParserV1();

        ArrayList<Token> tokens = new ArrayList<>(List.of(
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "name"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "String"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "lastName"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "Number"),
                new Token(TokenType.ASSIGNATION, "="),
                new Token(TokenType.NUMBER, "90"),
                new Token(TokenType.SEMICOLON, ";")
        ));

        AbstractSyntaxTree ast = parser.parse(tokens);

        assertEquals(2, ast.root.children.size());
        String[] typeNodes = new String[]{"Declare", "AssignDeclare"};
        for (int i = 0; i < typeNodes.length; i++) {
            assertEquals(typeNodes[i], ast.root.children.get(i).type);
        }
    }

    @Test
    public void testParseDeclarationBooleanTypeBetweenVersions() {
        Parser parserV1 = new ParserV1();
        Parser parserV2 = new ParserV2();

        ArrayList<Token> tokens = new ArrayList<>(List.of(
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "name"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.STRINGTYPE, "String"),
                new Token(TokenType.SEMICOLON, ";"),
                new Token(TokenType.KEYWORD, "let"),
                new Token(TokenType.IDENTIFIER, "live"),
                new Token(TokenType.DECLARATION, ":"),
                new Token(TokenType.BOOLEANTYPE, "Boolean"),
                new Token(TokenType.SEMICOLON, ";")
        ));

        AbstractSyntaxTree astV1 = parserV1.parse(tokens);
        AbstractSyntaxTree astV2 = parserV2.parse(tokens);

        assertEquals(1, astV1.root.children.size());
        assertEquals(2, astV2.root.children.size());
    }
}
