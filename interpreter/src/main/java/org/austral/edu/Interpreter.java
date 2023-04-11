package org.austral.edu;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter {

    public void interpret(ArrayList<AbstractSyntaxTree> trees){
        HashMap<String,String> types = new HashMap<>();
        HashMap<String,String> values = new HashMap<>();

        ArrayList<InterpreterStrategy> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter());
        interpreters.add(new IdentifierInterpreter());
        interpreters.add(new KeywordInterpreter());

        for (AbstractSyntaxTree tree: trees) {
            for (InterpreterStrategy interpreter: interpreters) {
                if (interpreter.validate(tree)) {interpreter.interpret(tree,types,values);
                }
            }
        }
    }
}
