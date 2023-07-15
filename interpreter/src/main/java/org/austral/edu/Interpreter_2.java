package org.austral.edu;

import ast.AbstractSyntaxTree;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter_2 {
    HashMap<String,String> types;
    HashMap<String,String> values;
    ArrayList<String> constants;
    Result result;
    
    Input input;

    public Interpreter_2(Result result, Input input){
        this.types = new HashMap<>();
        this. values = new HashMap<>();
        this.constants = new ArrayList<>();
        this.result = result;
        this.input = input;
    }
    public Interpreter_2(HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input){
        this.types = types;
        this. values = values;
        this.constants = constants;
        this.result = result;
        this.input = input;
    }

    public void interpret(AbstractSyntaxTree ast) throws IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, InterpretException, AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {

        ArrayList<InterpreterStrategy_2> interpreters = new ArrayList<>();
        interpreters.add(new FunctionInterpreter_2());
        interpreters.add(new IdentifierInterpreter_2());
        interpreters.add(new KeywordInterpreter_2());
        interpreters.add(new PrintInterpreter_2());
        interpreters.add(new IfInterpreter());

        for (Node sentence: ast.getChildren()) {
            for (InterpreterStrategy_2 interpreter : interpreters) {
                if (interpreter.validate(sentence)) {
                    interpreter.interpret(sentence, types, values, constants, result, input);
                    break;
                }
            }
        }
    }

    public Result getResult() {
        return result;
    }
}
