package parser;

import ast.AbstractSyntaxTree;
import ast.AssignDeclareNode;
import ast.LiteralNode;
import ast.Node;
import exceptions.*;
import org.austral.edu.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException, UnclosedBracesException {
        List<Token> tokens = lexerV2.lex(new StringInput("""
                if(true){
                    println("true 1");
                    const x:string = "Nacho";
                    let y:string = x;
                    println(y);
                }else{
                    println("false 1");
                }
                if(false){
                    println("true 2");
                }else{
                    println("false 2");
                    let x:number = readInput("Please enter a number");
                    let y:number = (4+2)*(3)/(9-x);
                    println(y);
                }
                let x:boolean;
                x = true;
                let y:number;
                y = readInput("Please enter a number");
                if(x){
                    println("true 3");
                    println(y);
                }else{
                    println("false 3");
                }
                """));

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
