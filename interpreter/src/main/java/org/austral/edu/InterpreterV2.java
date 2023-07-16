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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InterpreterV2 {
    HashMap<String,String> types;
    HashMap<String,String> values;
    ArrayList<String> constants;
    Result result;
    
    Input input;

    List<InterpreterStrategy_2> strategies = Arrays.asList(new FunctionInterpreter(), new IdentifierInterpreter_2(), new KeywordInterpreter_2());

    public InterpreterV2(Result result, Input input){
        this.types = new HashMap<>();
        this. values = new HashMap<>();
        this.constants = new ArrayList<>();
        this.result = result;
        this.input = input;
    }
    public InterpreterV2(HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result, Input input){
        this.types = types;
        this. values = values;
        this.constants = constants;
        this.result = result;
        this.input = input;
    }

    public void interpret(AbstractSyntaxTree ast) throws IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, InterpretException, AssignationException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        for (Node sentence: ast.getChildren()) {
            for (InterpreterStrategy_2 interpreter : strategies) {
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
