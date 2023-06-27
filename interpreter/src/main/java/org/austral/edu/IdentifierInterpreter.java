package org.austral.edu;

import org.austral.edu.Nodes.AssignNode;
import org.austral.edu.Nodes.NameNode;
import org.austral.edu.Nodes.Node;

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
        return node.type.equals("Assignation");
    }

    @Override
    public void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values) throws AssignationError, IncompatibilityError, NotDefinedError {
        if (types.isEmpty()){
            throw new NotDefinedError();
        }else{
            AssignNode assignNode = (AssignNode) node;
            NameNode nameNode = (NameNode) assignNode.children.get(0);
            Node valueName = assignNode.children.get(1);

            for (SubInterpreterStrategy strategy: strategies) {
                if (strategy.validate(valueName)){
                    String message = strategy.interpret(valueName,types,values);
                    if (message.equals("Error")){
                        throw new AssignationError();
                    }else {
                        values.put(nameNode.content, message);
                        break;
                    }
                }
            }

            if (isString(types, values, nameNode)){

            }else if(isNumber(types, values, nameNode)){

            }else{
                throw new IncompatibilityError();
            }
        }
    }

    private boolean isString(HashMap<String, String> types, HashMap<String, String> values, NameNode nameNode) {
        return types.get(nameNode.content).equals("String") && !isInteger(values.get(nameNode.content));
    }

    private boolean isNumber(HashMap<String, String> types, HashMap<String, String> values, NameNode nameNode) {
        return types.get(nameNode.content).equals("Number") && isInteger(values.get(nameNode.content));
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
