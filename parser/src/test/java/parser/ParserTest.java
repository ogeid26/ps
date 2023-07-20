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
        InputProvider string1 = new StringInput("let firstName: string = \"Miguel\"; ");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        assertTrue(ast.getChildren().get(0) instanceof AssignDeclareNode);

        assertTrue(((AssignDeclareNode) ast.getChildren().get(0)).getDeclareNode() instanceof DeclareNode);
        assertEquals("string", ((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(0)).getDeclareNode()).getTypeNode().getContent());
        assertEquals("firstName", ((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(0)).getDeclareNode()).getNameNode().getContent());

        assertTrue(((AssignDeclareNode) ast.getChildren().get(0)).getExpressionNode() instanceof LiteralNode);
        assertEquals( "Miguel", ((LiteralNode) ((AssignDeclareNode) ast.getChildren().get(0)).getExpressionNode()).value.getContent());

        printNode(ast);

    }

    @Test
    public void test002_Declare_then_Assign() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x: number; " +
                "x = (1 + 2);");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        assertEquals("number",ast.getChildren().get(0).getChildren().get(0).getContent());
        assertEquals("x",ast.getChildren().get(0).getChildren().get(1).getContent());

        assertEquals("x",ast.getChildren().get(1).getChildren().get(0).getContent());

        assertEquals("Sum",ast.getChildren().get(1).getChildren().get(1).getType());

        assertEquals("1",((LiteralNode) (((AdditionNode) (ast.getChildren().get(1).getChildren().get(1))).getLeft())).value.getContent());
        assertEquals("2",((LiteralNode) (((AdditionNode) (ast.getChildren().get(1).getChildren().get(1))).getRight())).value.getContent());

        printNode(ast);

    }

    @Test
    public void test003_Assign_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let first_name: string = \"Miguel\"; " +
                "let y: string;" +
                "y = first_name;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        assertEquals("y",((AssignNode) ast.getChildren().get(2)).getIdentifierNode().getContent());
        assertEquals("first_name", (((LiteralNode)(((AssignNode) ast.getChildren().get(2)).getExpressionNode()))).value.getContent());

        printNode(ast);

    }

    @Test
    public void test004_Assign_Declare_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x: number = 100; " +
                "let y: number = x;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        assertEquals("number",((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(1)).getDeclareNode()).getTypeNode().getContent());
        assertEquals("y", ((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(1)).getDeclareNode()).getNameNode().getContent());
        assertEquals("x", ((LiteralNode) ((AssignDeclareNode) ast.getChildren().get(1)).getExpressionNode()).value.getContent());

        printNode(ast);

    }

    @Test
    public void test005_Println() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x:number = 2;" +
                "println(6/3-1*x);");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertEquals( "Print", ast.children.get(1).getType());

        assertEquals( "6",((LiteralNode)((DivisionNode)((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft()).getLeft()).value.getContent());

        assertEquals("3",( (LiteralNode)((DivisionNode)((SubtractionNode) ast.children.get(1).getChildren().get(0)).getLeft()).getRight()).value.getContent());

        assertEquals("1",((LiteralNode)((MultiplicationNode)((SubtractionNode) ast.children.get(1).getChildren().get(0)).getRight()).getLeft()).value.getContent());

        assertEquals( "x",((LiteralNode)((MultiplicationNode)((SubtractionNode) ast.children.get(1).getChildren().get(0)).getRight()).getRight()).value.getContent());

        printNode(ast);

    }
    @Test
    public void test006_Boolean() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x:boolean = true;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertEquals("boolean", ((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(0)).getDeclareNode()).getTypeNode().getContent());
        assertEquals("x", ((DeclareNode) ((AssignDeclareNode) ast.getChildren().get(0)).getDeclareNode()).getNameNode().getContent());

        assertEquals("true", ((LiteralNode) ((AssignDeclareNode) ast.getChildren().get(0)).getExpressionNode()).value.getContent());

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

        assertEquals("true",ast.getChildren().get(0).getContent());

        assertEquals("x",((LiteralNode) ((PrintNode) ((IfNode) ast.getChildren().get(0)).getTrueTree().getChildren().get(0)).getExpressionNode()).value.getContent());
        assertEquals("y",((LiteralNode) ((PrintNode) ((IfNode) ast.getChildren().get(0)).getFalseTree().getChildren().get(0)).getExpressionNode()).value.getContent());

        printNode(ast);
    }

    @Test
    public void test008_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("const x:string = readInput(\"Enter your Name\");");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertEquals("string", ((DeclareNode) ((DeclareReaderNode) ast.getChildren().get(0)).getDeclareNode()).getTypeNode().getContent());
        assertEquals("x",((DeclareNode) ((DeclareReaderNode) ast.getChildren().get(0)).getDeclareNode()).getNameNode().getContent());

        assertEquals("Enter your Name",((ReaderNode) ((DeclareReaderNode) ast.getChildren().get(0)).getReaderNode()).getContent());

        printNode(ast);

    }

    @Test
    public void test009_Assign_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException {
        InputProvider string1 = new StringInput("let x:number;" +
                "x = readInput(\"Enter value\");");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertEquals("x", ((IdentifierNode) ((AssignReaderNode) ast.getChildren().get(1)).getIdentifierNode()).getContent());

        assertEquals("Enter value", ((ReaderNode) ((AssignReaderNode) ast.getChildren().get(1)).getReaderNode()).getContent());

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
