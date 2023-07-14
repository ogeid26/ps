package org.austral.edu.InnerInterpreters;

import ast.Node;
import org.austral.edu.Exceptions.EmptyContentException;
import org.austral.edu.Helpers.TextHelper;
import org.austral.edu.Nodes.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ReaderInterpreter implements SubInterpreterStrategy{
    TextHelper textHelper = new TextHelper();
    @Override
    public boolean validate(Node node) {
        return node.type.equals("ReadInput");
    }

    @Override
    public Node interpret(Node node, HashMap<String, String> types, HashMap<String, String> values) throws EmptyContentException {
        System.out.println(node.content);
        Scanner newInput = new Scanner(System.in);
        String answer = newInput.nextLine();
        if (Objects.equals(answer, "")){
            throw new EmptyContentException();
        }else {
            Object result = textHelper.parseString(answer);
            if (result instanceof Double || result instanceof Integer) {
                return new ValueNumberNode(result.toString());
            }else{
                if (result.equals("FALSE")){
                    return new FalseNode();
                } else if (result.equals("TRUE")) {
                    return new TrueNode();
                }else {
                    return new ValueStringNode(result.toString());
                }
            }
        }
    }
}
