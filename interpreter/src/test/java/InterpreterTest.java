import org.austral.edu.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    @Test
    public void testing(){
        KeywordInterpreter i = new KeywordInterpreter();
        Interpreter interpreter = new Interpreter();

        ArrayList<AbstractSyntaxTree> trees = new ArrayList<>();
        AbstractSyntaxTree tree = new AbstractSyntaxTree();
        AbstractSyntaxTree tree2 = new AbstractSyntaxTree();

        Node n1 = new ValueNode("1");
        Node n2 = new ValueNode("number");
        Node n3 = new MathNode("-", new ArrayList<>(Arrays.asList(n2,n1)));
        Node n4 = new NameNode("value");
        Node n5 = new TypeNode("String");
        Node n6 = new DeclareNode("Declaration", new ArrayList<>(Arrays.asList(n5,n4)));
        tree.addSentence(new AssignDeclareNode("Assignation", new ArrayList<>(Arrays.asList(n6,n3))));
        tree2.addSentence(new FunctionNode("Print",new ArrayList<>(List.of(n4))));
        HashMap<String,String> types = new HashMap<>();
        HashMap<String,String> values = new HashMap<>();

        trees.add(tree);
        trees.add(tree2);

        interpreter.interpret(trees);
        //assertEquals("Success",i.interpret(tree.getFirstNode(), types,values));
    }
}
