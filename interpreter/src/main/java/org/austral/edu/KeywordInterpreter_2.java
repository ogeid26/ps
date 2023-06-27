package org.austral.edu;

import org.austral.edu.Errors.AssignationError;
import org.austral.edu.Errors.EmptyContentError;
import org.austral.edu.Errors.IncompatibilityError;
import org.austral.edu.Errors.ValueNotFoundError;
import org.austral.edu.Nodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeywordInterpreter_2 implements InterpreterStrategy_2{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter(), new BinaryInterpreter(), new ReaderInterpreter()));
    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node) || isConstant(node));
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants) throws AssignationError, IncompatibilityError, EmptyContentError, ValueNotFoundError {
        if (isAssignDeclare(node)){
            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node valueNode = assignDeclareNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueNode)){
                    try{
                        String message = strategy.interpret(valueNode,types,values);
                        if (isString(declareNode, message, valueNode)){
                            values.put(declareNode.getNameNode().content, message);
                            break;
                        }else if(isNumber(declareNode, message)){
                            values.put(declareNode.getNameNode().content, message);
                            break;
                        }else if(isBoolean(declareNode, valueNode)) {
                            values.put(declareNode.getNameNode().content, message);
                            break;
                        }else{
                            throw new IncompatibilityError();
                        }
                    }catch (Error e){
                        System.out.println(e.getMessage());
                        throw new AssignationError();
                    }
                }
            }

        }else if(isConstant(node)) {
            ConstantNode constantNode = (ConstantNode) node;
            DeclareNode declareNode = constantNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node valueNode = constantNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueNode)){
                    try{
                        String message = strategy.interpret(valueNode,types,values);
                        if (isString(declareNode, message, valueNode)){
                            values.put(declareNode.getNameNode().content, message);
                            constants.add(declareNode.getNameNode().content);
                            break;
                        }else if(isNumber(declareNode, message)){
                            values.put(declareNode.getNameNode().content, message);
                            constants.add(declareNode.getNameNode().content);
                            break;
                        }else if(isBoolean(declareNode, valueNode)) {
                            values.put(declareNode.getNameNode().content, message);
                            constants.add(declareNode.getNameNode().content);
                            break;
                        }else{
                            throw new IncompatibilityError();
                        }
                    }catch (Error e){
                        System.out.println(e.getMessage());
                        throw new AssignationError();
                    }
                }
            }
        }else{
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().content, declareNode.getTypeNode().content);
        }
    }

    private boolean isNumber(DeclareNode declareNode, String message) {
        return declareNode.getTypeNode().content.equals("Number") && isInteger(message);
    }

    private boolean isString(DeclareNode declareNode, String message, Node valueNode) {
        return declareNode.getTypeNode().content.equals("String") && !isInteger(message) && !(valueNode instanceof BinaryNode);
    }

    private boolean isBoolean(DeclareNode declareNode, Node valueNode) {
        return declareNode.getTypeNode().content.equals("Boolean") && (valueNode instanceof BinaryNode);
    }

    private boolean isDeclare(Node node) {
        return node.type.equals("Declare");
    }

    private boolean isAssignDeclare(Node node) {
        return node.type.equals("AssignDeclare");
    }

    private boolean isConstant(Node node) {
        return node.type.equals("Constant");
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
