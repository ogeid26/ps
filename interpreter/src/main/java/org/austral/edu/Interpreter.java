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
                Node node = tree.root.children.get(0);
                if (interpreter.validate(node)) {
                    interpreter.interpret(node,types,values);
                }
            }
        }
    }
}
