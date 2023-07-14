package org.austral.edu;

import ast.AbstractSyntaxTree;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter_2 {
    HashMap<String,String> types;
    HashMap<String,String> values;
    ArrayList<String> constants;
    Result result;

    public Interpreter_2(Result result){
        this.types = new HashMap<>();
        this. values = new HashMap<>();
        this.constants = new ArrayList<>();
        this.result = result;
    }
    public Interpreter_2(HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result){
        this.types = types;
        this. values = values;
        this.constants = constants;
        this.result = result;
    }

    public void interpret(ArrayList<AbstractSyntaxTree> trees) throws IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, InterpretException, AssignationException {

        ArrayList<InterpreterStrategy_2> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter_2());
        interpreters.add(new IdentifierInterpreter_2());
        interpreters.add(new KeywordInterpreter_2());

        for (AbstractSyntaxTree tree: trees) {
            for (InterpreterStrategy_2 interpreter: interpreters) {
                Node node = tree.getChildren().get(0);
                if (interpreter.validate(node)) {
                    interpreter.interpret(node,types,values,constants, result);
                    break;
                }
            }
        }
    }

    public Result getResult() {
        return result;
    }
}
