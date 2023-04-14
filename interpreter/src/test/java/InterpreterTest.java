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
        AbstractSyntaxTree tree = new AbstractSyntaxTree();

        Node n1 = new ValueNode("'Peter'");
        Node n2 = new NameNode("name");
        Node n3 = new TypeNode("String");
        Node n5 = new DeclareNode("Declaration", new ArrayList<>(Arrays.asList(n3,n2)));
        tree.addSentence(new AssignDeclareNode("Assignation", new ArrayList<>(Arrays.asList(n5,n1))));
        HashMap<String,String> types = new HashMap<>();
        HashMap<String,String> values = new HashMap<>();

        assertEquals("Success",i.interpret(tree.getFirstNode(), types,values));
    }
}
