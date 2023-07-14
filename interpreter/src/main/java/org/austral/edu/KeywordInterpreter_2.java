package org.austral.edu;

import ast.AssignDeclareNode;
import ast.DeclareNode;
import ast.Node;
import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.*;
import org.austral.edu.Nodes.*;
import org.austral.edu.Results.Result;

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
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws AssignationException, IncompatibilityException {
        if (isAssignDeclare(node)){
            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().content,declareNode.getTypeNode().content);
            Node valueNode = assignDeclareNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueNode)){
                    try{
                        Node answer = strategy.interpret(valueNode,types,values);
                        if (isValueValidForType(declareNode, answer)){
                            values.put(declareNode.getNameNode().content, answer.content);
                            isReader(result, strategy, answer.content);
                            break;
                        }else{
                            throw new IncompatibilityException();
                        }
                    }catch (InterpretException e){
                        System.out.println(e.getMessage());
                        throw new AssignationException();
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
                        Node answer = strategy.interpret(valueNode,types,values);
                        if (isValueValidForType(declareNode, answer)){
                            values.put(declareNode.getNameNode().content, answer.content);
                            constants.add(declareNode.getNameNode().content);
                            isReader(result, strategy, answer.content);
                            break;
                        }else{
                            throw new IncompatibilityException();
                        }
                    }catch (InterpretException e){
                        System.out.println(e.getMessage());
                        throw new AssignationException();
                    }
                }
            }
        }else{
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().content, declareNode.getTypeNode().content);
        }
    }

    private boolean isValueValidForType(DeclareNode declareNode, Node answer) {
        return isString(declareNode, answer) ||
                isNumber(declareNode, answer) ||
                isBoolean(declareNode, answer);
    }

    private void isReader(Result result, SubInterpreterStrategy strategy, String message) {
        if (strategy instanceof ReaderInterpreter){
            result.saveReaderElement(message);
        }
    }

    private boolean isNumber(DeclareNode declareNode, Node answer) {
        return declareNode.getTypeNode().content.equals("Number") && answer instanceof ValueNumberNode;
    }

    private boolean isString(DeclareNode declareNode, Node answer) {
        return declareNode.getTypeNode().content.equals("String") && answer instanceof ValueStringNode;
    }

    private boolean isBoolean(DeclareNode declareNode, Node answer) {
        return declareNode.getTypeNode().content.equals("Boolean") && answer instanceof BinaryNode;
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
}
