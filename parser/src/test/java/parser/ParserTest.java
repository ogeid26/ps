package parser;

import ast.AbstractSyntaxTree;
import ast.AssignDeclareNode;
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
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        List<Token> tokens = lexerV2.lex(new StringInput("""
                if (true) {
                    let x: string = "Miguel";
                    const y: number;
                } else {
                    let age: number = 23;
                }
                """));

        AbstractSyntaxTree ast = parserV2.parse(tokens);

        assertEquals(3,3);

    }
}
