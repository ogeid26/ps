package org.austral.edu;

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
    public String interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws AssignationError {
        System.out.println(node.content);
        Scanner myObj = new Scanner(System.in);
        String result = myObj.nextLine();
        if (Objects.equals(result, "")){
            return "Error";
        }else {
            return result;
        }
    }
}
