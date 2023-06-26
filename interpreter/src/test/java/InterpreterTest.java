import org.austral.edu.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    @Test
    public void testing() throws AssignationError, NotDefinedError, IncompatibilityError {
        KeywordInterpreter i = new KeywordInterpreter();
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
        Node n6 = new DeclareNode("Declaration", new ArrayList<>(Arrays.asList(n5,n4)));
        tree.addSentence(new AssignDeclareNode("Assignation", new ArrayList<>(Arrays.asList(n6,n111))));
        tree2.addSentence(new FunctionNode("Print",new ArrayList<>(List.of(n4))));

        trees.add(tree);
        trees.add(tree2);

        interpreter.interpret(trees);
        //assertEquals("Success",i.interpret(tree.getFirstNode(), types,values));
    }
}
