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

        Node n1 = new Node("'Peter'");
        Node n2 = new Node("name");
        Node n3 = new Node("String");
        Node n4 = new Node("literal", new ArrayList<>(List.of(n1)));
        Node n5 = new Node("Declaration", new ArrayList<>(Arrays.asList(n3,n2)));
        tree.addSentence("Assignation", new ArrayList<>(Arrays.asList(n5,n4)));
        HashMap<String,String> types = new HashMap<>();
        HashMap<String,String> values = new HashMap<>();

        assertTrue(i.validate(tree));
    }
}
