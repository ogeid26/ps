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

public class InterpreterTest {
    /*
    @Test
    public void testing_Version_1() throws AssignationException, NotDefinedException, IncompatibilityException, InterpretException {
        Interpreter interpreter = new Interpreter(new ClassicResult());

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree tree = new AbstractSyntaxTree();
        AbstractSyntaxTree tree2 = new AbstractSyntaxTree();

        Node n1 = new ValueNode("4");
        Node n2 = new ValueNode("6");
        Node n3 = new ExpressionNode("+", new ArrayList<>(Arrays.asList(n2,n1)));
        Node n11 = new ValueNode("15");
        Node n22 = new ValueNode("7");
        Node n33 = new ExpressionNode("*", new ArrayList<>(Arrays.asList(n22,n11)));
        Node n111 = new ExpressionNode("/", new ArrayList<>(Arrays.asList(n33,n3)));
        Node n4 = new IdentifierNode("value");
        Node n5 = new TypeNode("Number");
        Node n6 = new DeclareNode((TypeNode) n5, (IdentifierNode) n4, new KeywordNode("let"));
        tree.addNode(new AssignDeclareNode((DeclareNode) n6, (ExpressionNode) n111));
        Node n7 = new PrintNode(new ArrayList<>(List.of(n4)));
        tree2.addNode(new FunctionNode(new ArrayList<>(List.of(n7))));

        trees.add(tree);
        trees.add(tree2);

        interpreter.interpret(trees);
        ClassicResult classicResult = (ClassicResult) interpreter.getResult();
        classicResult.print();
    }

    @Test
    public void testing_Version_2() throws InterpretException, NotDefinedException, IncompatibilityException, ConstantVariableException, IllogicalConditionalException, AssignationException {
        Interpreter_2 interpreter = new Interpreter_2(new ClassicResult());

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree start = new AbstractSyntaxTree();
        AbstractSyntaxTree ifTree = new AbstractSyntaxTree();
        AbstractSyntaxTree continueTree = new AbstractSyntaxTree();

        ArrayList<AbstractSyntaxTree> trueTrees = new ArrayList<>();
        AbstractSyntaxTree trueTree = new AbstractSyntaxTree();

        ArrayList<AbstractSyntaxTree> falseTrees = new ArrayList<>();
        AbstractSyntaxTree falseTree = new AbstractSyntaxTree();

        Node n1 = new FalseNode();
        Node n2 = new IdentifierNode("value");
        Node n3 = new TypeNode("Boolean");
        Node n4 = new DeclareNode((TypeNode) n3, (IdentifierNode) n2, new KeywordNode("let"));
        start.addNode(new AssignDeclareNode((DeclareNode) n4, (ExpressionNode) n1));

        Node n5 = new PrintNode(new ArrayList<>(List.of(new ValueNode("2"))));
        Node n6 = new FunctionNode(new ArrayList<>(List.of(n5)));
        trueTree.addNode(n6);
        trueTrees.add(trueTree);

        Node n7 = new PrintNode(new ArrayList<>(List.of(new ValueNode("8"))));
        Node n8 = new FunctionNode(new ArrayList<>(List.of(n7)));
        falseTree.addNode(n8);
        falseTrees.add(falseTree);

        Node n9 = new IfNode(n2,trueTrees,falseTrees);
        ifTree.addNode(new FunctionNode(new ArrayList<>(List.of(n9))));

        Node n10 = new PrintNode(new ArrayList<>(List.of(new ValueNode("Exito"))));
        Node n11 = new FunctionNode(new ArrayList<>(List.of(n10)));
        continueTree.addNode(n11);

        trees.add(start);
        trees.add(ifTree);
        trees.add(continueTree);

        interpreter.interpret(trees);
        ClassicResult classicResult = (ClassicResult) interpreter.getResult();
        classicResult.print();
    }

    @Test
    public void testing_constants() throws InterpretException, NotDefinedException, IncompatibilityException, ConstantVariableException, IllogicalConditionalException, AssignationException {
        Interpreter_2 interpreter = new Interpreter_2(new ClassicResult());

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree start = new AbstractSyntaxTree();
        AbstractSyntaxTree continueTree = new AbstractSyntaxTree();

        Node n1 = new FalseNode();
        Node n2 = new IdentifierNode("value");
        Node n3 = new TypeNode("Boolean");
        Node n4 = new DeclareNode((TypeNode) n3, (IdentifierNode) n2, new KeywordNode("let"));
        start.addNode(new ConstantNode(new ArrayList<>(Arrays.asList(n4,n1))));

        Node n5 = new TrueNode();
        Node n6 = new AssignNode((IdentifierNode) n2, (ExpressionNode) n5);
        continueTree.addNode(n6);

        trees.add(start);
        trees.add(continueTree);

        interpreter.interpret(trees);
    }

     */
/*
    @Test
    public void testing_readInput() throws AssignationError, NotDefinedError, IncompatibilityError, ConstantVariableError, ValueNotFoundError, EmptyContentError, IllogicalConditionalError {
        Interpreter_2 interpreter = new Interpreter_2(new ClassicResult());

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree start = new AbstractSyntaxTree();

        Node n1 = new ReadInputNode("Please enter a Boolean");
        Node n2 = new NameNode("value");
        Node n3 = new TypeNode("Boolean");
        Node n4 = new DeclareNode(new ArrayList<>(Arrays.asList(n3,n2)));
        start.addSentence(new ConstantNode(new ArrayList<>(Arrays.asList(n4,n1))));

        trees.add(start);

        interpreter.interpret(trees);
        ClassicResult classicResult = (ClassicResult) interpreter.getResult();
        classicResult.showInputs();
    }

 */

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
    public void sandbox() throws UnclosedParenthesesException, UnclosedStringLiteralException, UnexpectedTokenException, IncompatibilityException, DividedByZeroException, AssignationException, InterpretException, IncompatibleOperationException, NotDefinedException, VariableDoesntExistsException, ConstantVariableException, IllogicalConditionalException, UnclosedBracesException, ExpectedTokenException {
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
