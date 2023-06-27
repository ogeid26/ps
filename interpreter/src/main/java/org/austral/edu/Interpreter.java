package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter {
    HashMap<String,String> types;
    HashMap<String,String> values;
    Result result;

    public Interpreter(Result result){
         this.types = new HashMap<>();
         this.values = new HashMap<>();
         this.result = result;
    }

    public void interpret(ArrayList<AbstractSyntaxTree> trees) throws AssignationError, IncompatibilityError, NotDefinedError, ValueNotFoundError, EmptyContentError {

        ArrayList<InterpreterStrategy> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter());
        interpreters.add(new IdentifierInterpreter());
        interpreters.add(new KeywordInterpreter());

        for (AbstractSyntaxTree tree: trees) {
            for (InterpreterStrategy interpreter: interpreters) {
                Node node = tree.getFirstNode();
                if (interpreter.validate(node)) {
                    interpreter.interpret(node,types,values,result);
                    break;
                }
            }
        }
    }

    public Result getResult() {
        return result;
    }
}
