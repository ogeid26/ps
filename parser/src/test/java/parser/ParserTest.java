package parser;

import ast.AbstractSyntaxTree;
import ast.AssignDeclareNode;
import exceptions.*;
import org.austral.edu.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    static Lexer lexerV1;
    static Parser parserV1;

    @BeforeAll
    public static void setup() {
        lexerV1 = new LexerImpl();
        parserV1 = new ParserV1();
    }

    @Test
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, DividedByZeroException, IncompatibleOperationException {
        List<Token> tokens = lexerV1.lex(new StringInput("""
                let x: number = 1+2+3+4*5+6+7;
                """));

        AbstractSyntaxTree ast = parserV1.parse(tokens);

        System.out.println(((AssignDeclareNode) ast.getChildren().get(0)).getExpressionNode().solve().getContent());
        assertEquals(3,3);
    }
}
