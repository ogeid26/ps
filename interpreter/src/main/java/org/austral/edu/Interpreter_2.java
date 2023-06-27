package org.austral.edu;

import org.austral.edu.Errors.*;
import org.austral.edu.Nodes.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter_2 {
    HashMap<String,String> types;
    HashMap<String,String> values;
    ArrayList<String> constants;

    public Interpreter_2(){
        this.types = new HashMap<>();
        this. values = new HashMap<>();
        this.constants = new ArrayList<>();
    }
    public Interpreter_2(HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants){
        this.types = types;
        this. values = values;
        this.constants = constants;
    }

    public void interpret(ArrayList<AbstractSyntaxTree> trees) throws AssignationError, IncompatibilityError, NotDefinedError, ConstantVariableError, ValueNotFoundError, EmptyContentError, IllogicalConditionalError {

        ArrayList<InterpreterStrategy_2> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter_2());
        interpreters.add(new IdentifierInterpreter_2());
        interpreters.add(new KeywordInterpreter_2());

        for (AbstractSyntaxTree tree: trees) {
            for (InterpreterStrategy_2 interpreter: interpreters) {
                Node node = tree.getFirstNode();
                if (interpreter.validate(node)) {
                    interpreter.interpret(node,types,values,constants);
                    break;
                }
            }
        }
    }
}
