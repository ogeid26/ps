package parser;

import ast.*;
import exceptions.*;
import org.austral.edu.*;
import org.austral.edu.Nodes.IfNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    static Lexer lexerV1;
    static Parser parserV1;
    static Lexer lexerV2;
    static Parser parserV2;

    @BeforeAll
    public static void setup() {
        lexerV1 = new LexerV1();
        parserV1 = new ParserV1();
        lexerV2 = new LexerV2();
        parserV2 = new ParserV2();
    }

    @Test
    public void test001_Declare_Assign() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let name: string = \"Miguel\"; ");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);
        printNode(ast);

    }

    @Test
    public void test002_Declare_then_Assign() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x: number; " +
                "x = 1 + 2;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);
        printNode(ast);

    }

    @Test
    public void test003_Assign_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let name: string = \"Miguel\"; " +
                "let y: string;" +
                "y = name;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);
        printNode(ast);

    }

    @Test
    public void test004_Assign_Declare_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x: number = 100; " +
                "let y: number = x;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);
        printNode(ast);

    }

    @Test
    public void test005_Println() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x:string = 2;" +
                "println(6/3-1*x);");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertTrue(ast.children.get(1) instanceof PrintNode);

        assertTrue(ast.children.get(1).getChildren().get(0) instanceof SubtractionNode);

        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft() instanceof DivisionNode);

        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft().getLeft() instanceof LiteralNode);
        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft().getRight() instanceof LiteralNode);


        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getRight() instanceof MultiplicationNode);

        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft().getLeft() instanceof LiteralNode);
        assertTrue(((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft().getRight() instanceof LiteralNode);

        printNode(ast);

    }
    @Test
    public void test006_Boolean() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x:boolean = true;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);
        printNode(ast);
    }

    @Test
    public void test007_If() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("if(true){" +
                "println(\"x\");" +
                "}else{" +
                "println(\"y\");" +
                "}");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertTrue(ast.children.get(0) instanceof IfNode);

        printNode(ast);
    }

    @Test
    public void test008_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("const x:string = readInput(\"Enter your Name\");");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);
        printNode(ast);

    }

    @Test
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException, UnclosedBracesException, ExpectedTokenException {
        List<Token> tokens = lexerV2.lex(new StringInput("let x:number;"));

        AbstractSyntaxTree ast = parserV2.parse(tokens);
        printNode(ast);

        assertEquals(3,3);

    }

    public void printNode(Node node) {
        if (node.getType().equals("Literal"))
            System.out.println("Type: " + node.getType() + ", Content: " + ((LiteralNode) node).value.getContent());
        else if (node.getContent().equals(""))
            System.out.println("Type: " + node.getType());
        else System.out.println("Type: " + node.getType() + ", Content: " + node.getContent());
        for (Node child: node.children) {
            if (child != null) printNode(child);
            else System.out.println("Null Node");
        }
    }
}
