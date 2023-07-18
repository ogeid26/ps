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

public class IdentifierInterpreterV2 implements InterpreterStrategyV2 {

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return isAssign(node) || isAssignReader(node);
    }

    private boolean isAssign(Node node) {
        return node.getType().equals("Assign");
    }

    private boolean isAssignReader(Node node) {
        return node.getType().equals("AssignReader");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input) throws IncompatibilityException, NotDefinedException, ConstantVariableException, AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        if (isAssign(node)) {
            AssignNode assignNode = (AssignNode) node;
            IdentifierNode nameNode = assignNode.getIdentifierNode();

            if(!types.containsKey(nameNode.getContent())){
                throw new NotDefinedException();

            }else if (constants.contains(nameNode.getContent())) {
                throw new ConstantVariableException();

            } else {
                Node valueNode = assignNode.getExpressionNode().solve(values, types);
                if (valueNode.getType().equals(types.get(nameNode.getContent()))) {
                    values.put(nameNode.getContent(), valueNode.getContent());
                } else {
                    throw new IncompatibilityException();
                }
            }
        }else{
            AssignReaderNode assignReaderNode = (AssignReaderNode) node;
            IdentifierNode nameNode = assignReaderNode.getIdentifierNode();

            if(!types.containsKey(nameNode.getContent())){
                throw new NotDefinedException();

            }else if (constants.contains(nameNode.getContent())) {
                throw new ConstantVariableException();

            } else {
                ReaderNode readerNode = assignReaderNode.getReaderNode();
                result.savePrintElement(readerNode.getContent());
                String value = input.input();
                switch (types.get(nameNode.getContent())) {
                    case "number": {
                        try {
                            double number = Double.parseDouble(value);
                            values.put(nameNode.getContent(), String.valueOf(number));
                        } catch (Exception e) {
                            throw new IncompatibilityException();
                        }
                    }
                    case "boolean": {
                        if (value.equals("true") || value.equals("false"))
                            values.put(nameNode.getContent(), value);
                        else throw new IncompatibilityException();
                    }
                    default: values.put(nameNode.getContent(), value);
                }
            }
        }
    }

}
