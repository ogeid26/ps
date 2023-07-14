package org.austral.edu;

import ast.AssignDeclareNode;
import ast.DeclareNode;
import ast.Node;
import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.MathInterpreter;
import org.austral.edu.InnerInterpreters.NameInterpreter;
import org.austral.edu.InnerInterpreters.SubInterpreterStrategy;
import org.austral.edu.InnerInterpreters.ValueInterpreter;
import org.austral.edu.Nodes.*;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeywordInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));
    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node));
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException, IncompatibilityException, ValueNotFoundException, EmptyContentException {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
            Node valueNode = assignDeclareNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueNode)){
                    try{
                        Node answer = strategy.interpret(valueNode,types,values);
                        if (isString(declareNode, answer)){
                            values.put(declareNode.getNameNode().getContent(), answer.getContent());
                            break;
                        }else if(isNumber(declareNode, answer)){
                            values.put(declareNode.getNameNode().getContent(), answer.getContent());
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
        }else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
        }
    }

    private boolean isNumber(DeclareNode declareNode, Node answer) {
        return declareNode.getTypeNode().getContent().equals("Number") && answer instanceof ValueNumberNode;
    }

    private boolean isString(DeclareNode declareNode, Node answer) {
        return declareNode.getTypeNode().getContent().equals("String") && answer instanceof ValueStringNode;
    }

    private boolean isDeclare(Node node) {
        return node.getType().equals("Declare");
    }

    private boolean isAssignDeclare(Node node) {
        return node.getType().equals("AssignDeclare");
    }
}
