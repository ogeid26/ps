package org.austral.edu;

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

public class IdentifierInterpreter implements InterpreterStrategy{
    ArrayList<SubInterpreterStrategy> strategies = new ArrayList<>(Arrays.asList(new MathInterpreter(),new NameInterpreter(), new ValueInterpreter()));

    @Override
    public boolean validate(Node node) {
        return isAssignation(node);
    }

    private boolean isAssignation(Node node) {
        return node.type.equals("Assign");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, Result result) throws AssignationException, IncompatibilityException, NotDefinedException, ValueNotFoundException, EmptyContentException {
        if (types.isEmpty()){
            throw new NotDefinedException();
        }else{
            AssignNode assignNode = (AssignNode) node;
            NameNode nameNode = (NameNode) assignNode.children.get(0);
            Node valueName = assignNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueName)){
                    try{
                        Node answer = strategy.interpret(valueName,types,values);
                        if (isString(types, nameNode, answer)){
                            values.put(nameNode.content, answer.content);
                            break;
                        }else if(isNumber(types, nameNode, answer)){
                            values.put(nameNode.content, answer.content);
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

    private boolean isString(HashMap<String, String> types, NameNode nameNode, Node answer) {
        return types.get(nameNode.content).equals("String") && answer instanceof ValueStringNode;
    }

    private boolean isNumber(HashMap<String, String> types, NameNode nameNode, Node answer) {
        return types.get(nameNode.content).equals("Number") && answer instanceof ValueNumberNode;
    }

}
