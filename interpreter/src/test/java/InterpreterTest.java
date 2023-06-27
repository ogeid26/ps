import org.austral.edu.*;
import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterpreterTest {
    @Test
    public void testing_Version_1() throws AssignationError, NotDefinedError, IncompatibilityError, ValueNotFoundError, EmptyContentError {
        Interpreter interpreter = new Interpreter();

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree tree = new AbstractSyntaxTree();
        AbstractSyntaxTree tree2 = new AbstractSyntaxTree();

        Node n1 = new ValueNode("4");
        Node n2 = new ValueNode("6");
        Node n3 = new MathNode("+", new ArrayList<>(Arrays.asList(n2,n1)));
        Node n11 = new ValueNode("15");
        Node n22 = new ValueNode("7");
        Node n33 = new MathNode("*", new ArrayList<>(Arrays.asList(n22,n11)));
        Node n111 = new MathNode("/", new ArrayList<>(Arrays.asList(n33,n3)));
        Node n4 = new NameNode("value");
        Node n5 = new TypeNode("Number");
        Node n6 = new DeclareNode(new ArrayList<>(Arrays.asList(n5,n4)));
        tree.addSentence(new AssignDeclareNode(new ArrayList<>(Arrays.asList(n6,n111))));
        Node n7 = new PrintNode(new ArrayList<>(List.of(n4)));
        tree2.addSentence(new FunctionNode(new ArrayList<>(List.of(n7))));

        trees.add(tree);
        trees.add(tree2);

        interpreter.interpret(trees);
    }

    @Test
    public void testing_Version_2() throws AssignationError, NotDefinedError, IncompatibilityError, ConstantVariableError, ValueNotFoundError, EmptyContentError, IllogicalConditionalError {
        Interpreter_2 interpreter = new Interpreter_2();

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree start = new AbstractSyntaxTree();
        AbstractSyntaxTree ifTree = new AbstractSyntaxTree();
        AbstractSyntaxTree continueTree = new AbstractSyntaxTree();

        ArrayList<AbstractSyntaxTree> trueTrees = new ArrayList<>();
        AbstractSyntaxTree trueTree = new AbstractSyntaxTree();

        ArrayList<AbstractSyntaxTree> falseTrees = new ArrayList<>();
        AbstractSyntaxTree falseTree = new AbstractSyntaxTree();

        Node n1 = new FalseNode();
        Node n2 = new NameNode("value");
        Node n3 = new TypeNode("Boolean");
        Node n4 = new DeclareNode(new ArrayList<>(Arrays.asList(n3,n2)));
        start.addSentence(new AssignDeclareNode(new ArrayList<>(Arrays.asList(n4,n1))));

        Node n5 = new PrintNode(new ArrayList<>(List.of(new ValueNode("2"))));
        Node n6 = new FunctionNode(new ArrayList<>(List.of(n5)));
        trueTree.addSentence(n6);
        trueTrees.add(trueTree);

        Node n7 = new PrintNode(new ArrayList<>(List.of(new ValueNode("8"))));
        Node n8 = new FunctionNode(new ArrayList<>(List.of(n7)));
        falseTree.addSentence(n8);
        falseTrees.add(falseTree);

        Node n9 = new IfNode(n2,trueTrees,falseTrees);
        ifTree.addSentence(new FunctionNode(new ArrayList<>(List.of(n9))));

        Node n10 = new PrintNode(new ArrayList<>(List.of(new ValueNode("Exito"))));
        Node n11 = new FunctionNode(new ArrayList<>(List.of(n10)));
        continueTree.addSentence(n11);

        trees.add(start);
        trees.add(ifTree);
        trees.add(continueTree);

        interpreter.interpret(trees);
    }
}
