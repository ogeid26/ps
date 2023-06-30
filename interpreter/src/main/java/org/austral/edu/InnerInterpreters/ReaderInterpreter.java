package org.austral.edu.InnerInterpreters;

import org.austral.edu.Exceptions.AssignationException;
import org.austral.edu.Exceptions.EmptyContentException;
import org.austral.edu.Nodes.Node;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ReaderInterpreter implements SubInterpreterStrategy{
    @Override
    public boolean validate(Node node) {
        return node.type.equals("ReadInput");
    }

    @Override
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentException {
        System.out.println(node.content);
        Scanner newInput = new Scanner(System.in);
        String result = newInput.nextLine();
        if (Objects.equals(result, "")){
            throw new EmptyContentException();
        }else {
            return result;
        }
    }
}
