package org.austral.edu;

import ast.AssignNode;
import ast.Node;
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
        return node.getType().equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws IncompatibilityException, NotDefinedException, ConstantVariableException, AssignationException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            assert assignNode.children != null;
            ValueVariableNode nameNode = (ValueVariableNode) assignNode.children.get(0);
            Node valueNode = assignNode.children.get(1);
            if (constants.contains(nameNode.getContent())){
                throw new ConstantVariableException();
            }else {
                for (SubInterpreterStrategy strategy : strategies) {
                    if (strategy.validate(valueNode)) {
                        try{
                            Node answer = strategy.interpret(valueNode,types,values);
                            if (isValueValidForType(types,nameNode,answer)){
                                values.put(nameNode.getContent(), answer.getContent());
                                isReader(result, strategy, answer.getContent());
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

    private boolean isValueValidForType(HashMap<String, String> types, ValueVariableNode nameNode, Node answer) {
        return isString(types, nameNode, answer) ||
                isNumber(types, nameNode, answer) ||
                isBoolean(types, nameNode, answer);
    }

    private void isReader(Result result, SubInterpreterStrategy strategy, String message) {
        if (strategy instanceof ReaderInterpreter){
            result.saveReaderElement(message);
        }
    }

    private boolean isString(HashMap<String, String> types, ValueVariableNode nameNode, Node answer) {
        return types.get(nameNode.getContent()).equals("String") && answer instanceof ValueStringNode;
    }

    private boolean isNumber(HashMap<String, String> types, ValueVariableNode nameNode, Node answer) {
        return types.get(nameNode.getContent()).equals("Number") && answer instanceof ValueNumberNode;
    }

    private boolean isBoolean(HashMap<String, String> types, ValueVariableNode nameNode, Node answer) {
        return types.get(nameNode.getContent()).equals("Boolean") && answer instanceof BinaryNode;
    }

}
