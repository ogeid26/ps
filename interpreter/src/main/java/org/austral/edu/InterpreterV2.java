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

    List<InterpreterStrategyV2> strategies = Arrays.asList(new FunctionInterpreter(), new IdentifierInterpreterV2(), new KeywordInterpreterV2());

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

    public void interpret(AbstractSyntaxTree ast) throws IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, InterpretException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException, UndefinedConstException {
        for (Node sentence: ast.getChildren()) {
            for (InterpreterStrategyV2 interpreter : strategies) {
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

    public HashMap<String, String> getTypes() {
        return types;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public ArrayList<String> getConstants() {
        return constants;
    }

    public Input getInput() {
        return input;
    }
}
