package org.austral.edu;

import ast.*;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class KeywordInterpreterV2 implements InterpreterStrategyV2 {

    @Override
    public boolean validate(Node node) {
        return (isAssignDeclare(node) || isDeclare(node) || isConstant(node) || isDeclareReader(node));
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input) throws AssignationException, IncompatibilityException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        if (isAssignDeclare(node)){

            AssignDeclareNode assignDeclareNode = (AssignDeclareNode) node;
            DeclareNode declareNode = assignDeclareNode.getDeclareNode();

            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
            Node valueNode = assignDeclareNode.getExpressionNode().solve(values, types);

            if (declareNode.getTypeNode().getContent().equals(valueNode.getType())){
                values.put(declareNode.getNameNode().getContent(), valueNode.getContent());
                if (declareNode.getKeywordNode().getContent().equals("const"))
                    constants.add(declareNode.getNameNode().getContent());
            }else{
                throw new IncompatibilityException();
            }
        } else if (isDeclareReader(node)) {
            DeclareReaderNode declareReaderNode = (DeclareReaderNode) node;
            DeclareNode declareNode = declareReaderNode.getDeclareNode();

            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
            ReaderNode readerNode = declareReaderNode.getReaderNode();

            result.savePrintElement(readerNode.getContent());
            String value = input.input();

            if (declareNode.getKeywordNode().getContent().equals("const"))
                constants.add(declareNode.getNameNode().getContent());

            String type = declareNode.getTypeNode().getContent();
            switch (type) {
                case "number": {
                    try {
                        double number = Double.parseDouble(value);
                        values.put(declareNode.getNameNode().getContent(), String.valueOf(number));
                    } catch (Exception e) {
                        throw new IncompatibilityException();
                    }
                }
            case "boolean": {
                    if (isaBoolean(value))
                        values.put(declareNode.getNameNode().getContent(), value);
                    else throw new IncompatibilityException();
                }
                default: values.put(declareNode.getNameNode().getContent(), value);
            }
        } else {
            DeclareNode declareNode = (DeclareNode) node;
            types.put(declareNode.getNameNode().getContent(), declareNode.getTypeNode().getContent());
        }
    }

    private boolean isaBoolean(String value) {
        return value.equals("true") || value.equals("false");
    }

    private boolean isDeclare(Node node) {
        return node.getType().equals("Declare");
    }

    private boolean isAssignDeclare(Node node) {
        return node.getType().equals("AssignDeclare");
    }

    private boolean isConstant(Node node) {
        return node.getType().equals("Constant");
    }

    private boolean isDeclareReader(Node node) {
        return node.getType().equals("DeclareReader");
    }
}
