package org.austral.edu;

import org.austral.edu.Exceptions.*;
import org.austral.edu.InnerInterpreters.*;
import org.austral.edu.Nodes.*;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IdentifierInterpreter_2 implements InterpreterStrategy_2{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(), new NameInterpreter(), new ValueInterpreter(), new BinaryInterpreter(), new ReaderInterpreter()));

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.type.equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws IncompatibilityException, NotDefinedException, ConstantVariableException, AssignationException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            NameNode nameNode = (NameNode) assignNode.children.get(0);
            Node valueNode = assignNode.children.get(1);
            if (constants.contains(nameNode.content)){
                throw new ConstantVariableException();
            }else {
                for (SubInterpreterStrategy strategy : strategies) {
                    if (strategy.validate(valueNode)) {
                        try{
                            String message = strategy.interpret(valueNode,types,values);
                            if (isValueValidForType(types,nameNode,message,valueNode)){
                                values.put(nameNode.content, message);
                                isReader(result, strategy, message);
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
            }
        }
    }

    private boolean isValueValidForType(HashMap<String, String> types, NameNode nameNode, String message, Node valueNode) {
        return isString(types,nameNode,message,valueNode) ||
                isNumber(types,nameNode,message) ||
                isBoolean(types, nameNode, valueNode);
    }

    private void isReader(Result result, SubInterpreterStrategy strategy, String message) {
        if (strategy instanceof ReaderInterpreter){
            result.saveReaderElement(message);
        }
    }

    private boolean isString(HashMap<String, String> types, NameNode nameNode, String message, Node valueNode) {
        return types.get(nameNode.content).equals("String") && !isInteger(message) && !(valueNode instanceof BinaryNode);
    }

    private boolean isNumber(HashMap<String, String> types, NameNode nameNode, String message) {
        return types.get(nameNode.content).equals("Number") && isInteger(message);
    }

    private boolean isBoolean(HashMap<String, String> types, NameNode nameNode, Node valueNode) {
        return types.get(nameNode.content).equals("Boolean") && (valueNode instanceof BinaryNode);
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
