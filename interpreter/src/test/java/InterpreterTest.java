import ast.*;
import exceptions.*;
import org.austral.edu.*;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Results.ClassicInput;
import org.austral.edu.Results.ClassicResult;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.ParserV1;
import parser.ParserV2;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InterpreterTest {
    static Lexer lexerV1;
    static Lexer lexerV2;
    static Parser parserV1;
    static Parser parserV2;

    @BeforeAll
    public static void setup() {
        lexerV1 = new LexerV1();
        lexerV2 = new LexerV2();
        parserV1 = new ParserV1();
        parserV2 = new ParserV2();
    }

    @Test
    public void test001_Declare_AssignV1() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException {
        InputProvider string1 = new StringInput("let firstName: string = \"Miguel\"; ");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV1 interpreter = new InterpreterV1(result);

        interpreter.interpret(ast);

        assertTrue(interpreter.getTypes().containsKey("firstName"));
        assertEquals("string", interpreter.getTypes().get("firstName"));
        assertEquals("Miguel", interpreter.getValues().get("firstName"));

    }

    @Test
    public void test002_Declare_then_AssignV1() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException {
        InputProvider string1 = new StringInput("let x: number; " +
                "x = 1 + 2;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV1 interpreter = new InterpreterV1(result);

        interpreter.interpret(ast);

        assertEquals("3", interpreter.getValues().get("x"));

    }

    @Test
    public void test003_Assign_VariableV1() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException {
        InputProvider string1 = new StringInput("let first_name: string = \"Miguel\"; " +
                "let y: string;" +
                "y = first_name;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV1 interpreter = new InterpreterV1(result);

        interpreter.interpret(ast);

        assertEquals("Miguel", interpreter.getValues().get("y"));

    }

    @Test
    public void test004_Assign_Declare_VariableV1() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException {
        InputProvider string1 = new StringInput("let x: number = 100; " +
                "let y: number = x;");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV1 interpreter = new InterpreterV1(result);

        interpreter.interpret(ast);

        assertEquals("100", interpreter.getValues().get("x"));
        assertEquals("100", interpreter.getValues().get("y"));

    }

    @Test
    public void test005_PrintlnV1() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException {
        InputProvider string1 = new StringInput("let x:number = 2;" +
                "println(x);");
        List<Token> tokens = lexerV1.lex(string1);
        AbstractSyntaxTree ast = parserV1.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV1 interpreter = new InterpreterV1(result);

        interpreter.interpret(ast);

        assertEquals("2", ((ClassicResult) interpreter.getResult()).getPrintElements().get(0));

    }

    @Test
    public void test001_Declare_AssignV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let firstName: string = \"Miguel\"; ");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result, null);

        interpreter.interpret(ast);

        assertTrue(interpreter.getTypes().containsKey("firstName"));
        assertEquals("string", interpreter.getTypes().get("firstName"));
        assertEquals("Miguel", interpreter.getValues().get("firstName"));

    }

    @Test
    public void test002_Declare_then_AssignV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let x: number; " +
                "x = 1 + 2;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result,null);

        interpreter.interpret(ast);

        assertEquals("3", interpreter.getValues().get("x"));

    }

    @Test
    public void test003_Assign_VariableV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let first_name: string = \"Miguel\"; " +
                "let y: string;" +
                "y = first_name;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result, null);

        interpreter.interpret(ast);

        assertEquals("Miguel", interpreter.getValues().get("y"));

    }

    @Test
    public void test004_Assign_Declare_VariableV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let x: number = 100; " +
                "let y: number = x;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result,null);

        interpreter.interpret(ast);

        assertEquals("100", interpreter.getValues().get("x"));
        assertEquals("100", interpreter.getValues().get("y"));

    }

    @Test
    public void test005_PrintlnV2() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let x:number = 3;" +
                "println(6/3-1*x);");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result, null);

        interpreter.interpret(ast);

        assertEquals("-1", ((ClassicResult) interpreter.getResult()).getPrintElements().get(0));

    }
    @Test
    public void test006_Boolean() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let x:boolean = true;");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();

        InterpreterV2 interpreter = new InterpreterV2(result, null);

        interpreter.interpret(ast);

        assertEquals("true", interpreter.getValues().get("x"));
    }

    @Test
    public void test007_If() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("if(true){" +
                "println(\"x\");" +
                "}else{" +
                "println(\"y\");" +
                "}");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);
        Result result = new ClassicResult();
        Input input = new ClassicInput("");

        InterpreterV2 interpreter = new InterpreterV2(result, input);

        interpreter.interpret(ast);

        assertEquals(1, ((ClassicResult) interpreter.getResult()).getPrintElements().size());
        assertEquals("x", ((ClassicResult) interpreter.getResult()).getPrintElements().get(0));
    }

    @Test
    public void test008_If_Variable() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let cond:boolean = false;" +
                "if(cond){" +
                "println(\"x\");" +
                "}else{" +
                "println(\"y\");" +
                "}");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);
        Result result = new ClassicResult();
        Input input = new ClassicInput("");

        InterpreterV2 interpreter = new InterpreterV2(result, input);

        interpreter.interpret(ast);

        assertEquals(1, ((ClassicResult) interpreter.getResult()).getPrintElements().size());
        assertEquals("y", ((ClassicResult) interpreter.getResult()).getPrintElements().get(0));
    }

    @Test
    public void test009_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("const x:string = readInput(\"Enter your Name\");" +
                "let y:string = \"Mendez\";");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();
        Input input = new ClassicInput("Nacho");

        InterpreterV2 interpreter = new InterpreterV2(result, input);

        interpreter.interpret(ast);

        assertEquals("Nacho", interpreter.getValues().get("x"));

        assertEquals(1, interpreter.getConstants().size());
        assertEquals("x", interpreter.getConstants().get(0));
    }

    @Test
    public void test010_Assign_ReadInput() throws UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException, UnexpectedTokenException, ExpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, ConstantVariableException, VariableDoesntExistsException, IllogicalConditionalException, UndefinedConstException {
        InputProvider string1 = new StringInput("let x:string;" +
                "x = readInput(\"Enter your Name\");");
        List<Token> tokens = lexerV2.lex(string1);
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();
        Input input = new ClassicInput("Nacho");

        InterpreterV2 interpreter = new InterpreterV2(result, input);

        interpreter.interpret(ast);

        assertEquals("Nacho", interpreter.getValues().get("x"));
    }

    @Test
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, IncompatibilityException, DividedByZeroException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UnclosedBracesException, ExpectedTokenException, UndefinedConstException {
        List<Token> tokens = lexerV2.lex(new StringInput("const x:string = \"Name\"; let y:string; y = x; if(true){ println(\"true\" + 1); }else{ println(\"false\"); }"));
        AbstractSyntaxTree ast = parserV2.parse(tokens);

        Result result = new ClassicResult();
        Input input = new ClassicInput("23");

        InterpreterV2 interpreterV2 = new InterpreterV2(result, input);

        interpreterV2.interpret(ast);

        printNode(ast);

        Assertions.assertEquals(3,3);
    }

    public void printNode(Node node) {
        System.out.println("Type: " + node.getType() + ", Content: " + node.getContent());
        for (Node child: node.children) {
            if (child != null) printNode(child);
            else System.out.println("Null Node");
        }
    }
}
