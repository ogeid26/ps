package org.austral.edu;

import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter_2 {
    HashMap<String,String> types;
    HashMap<String,String> values;

    public Interpreter_2(){
        this.types = new HashMap<>();
        this. values = new HashMap<>();
    }
    public Interpreter_2(HashMap<String,String> types, HashMap<String,String> values){
        this.types = types;
        this. values = values;
    }

    public void interpret(ArrayList<AbstractSyntaxTree> trees) throws AssignationError, IncompatibilityError, NotDefinedError {

        ArrayList<InterpreterStrategy> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter_2());
        interpreters.add(new IdentifierInterpreter_2());
        interpreters.add(new KeywordInterpreter_2());

        for (AbstractSyntaxTree tree: trees) {
            for (InterpreterStrategy interpreter: interpreters) {
                Node node = tree.getFirstNode();
                if (interpreter.validate(node)) {
                    interpreter.interpret(node,types,values);
                    break;
                }
            }
        }
    }
}
