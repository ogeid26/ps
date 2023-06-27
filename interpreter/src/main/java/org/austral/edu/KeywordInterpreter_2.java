package org.austral.edu;

import org.austral.edu.Nodes.AssignDeclareNode;
import org.austral.edu.Nodes.DeclareNode;
import org.austral.edu.Nodes.NameNode;
import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeywordInterpreter_2 implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter(), new BinaryInterpreter(), new ReaderInterpreter()));
    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node));
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node n = assignDeclareNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(n)){
                    String message = strategy.interpret(n,types,values);
                    if (message.equals("Error")){
                        throw new AssignationError();
                    }else {
                        values.put(declareNode.getNameNode().content, message);
                        break;
                    }
                }
            }
            if (isString(types, values, declareNode)){

            }else if(isNumber(types, values, declareNode)){

            }else if(isBoolean(types, values, declareNode)) {

            }else{
                throw new IncompatibilityError();
            }
        }else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().content, declareNode.getTypeNode().content);
        }
    }

    private boolean isNumber(HashMap<String, String> types, HashMap<String, String> values, DeclareNode declareNode) {
        return types.get(declareNode.getNameNode().content).equals("Number") && isInteger(values.get(declareNode.getNameNode().content));
    }

    private boolean isString(HashMap<String, String> types, HashMap<String, String> values, DeclareNode declareNode) {
        return types.get(declareNode.getNameNode().content).equals("String") && !isInteger(values.get(declareNode.getNameNode().content));
    }

    private boolean isBoolean(HashMap<String, String> types, HashMap<String, String> values, DeclareNode declareNode) {
        return types.get(declareNode.getNameNode().content).equals("Boolean") && isaBoolean(values,declareNode);
    }

    private boolean isaBoolean(HashMap<String, String> values, DeclareNode declareNode) {
        return values.get(declareNode.getNameNode().content).equals("TRUE") || values.get(declareNode.getNameNode().content).equals("FALSE");
    }

    private boolean isDeclare(Node node) {
        return node.type.equals("Declare");
    }

    private boolean isAssignDeclare(Node node) {
        return node.type.equals("AssignDeclare");
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
