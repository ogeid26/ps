package org.austral.edu;

import ast.AbstractSyntaxTree;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Interpreter {
    HashMap<String,String> types;
    HashMap<String,String> values;
    
    Result result;

    InterpreterStrategy[] interpreters;

    public Interpreter(Result result, InterpreterStrategy[] interpreters){
         this.types = new HashMap<>();
         this.values = new HashMap<>();
         this.interpreters = interpreters;
         this.result = result;
    }

    public void interpret(AbstractSyntaxTree ast) throws AssignationException, IncompatibilityException, NotDefinedException, InterpretException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        for (Node sentence: ast.getChildren()) {
            for (InterpreterStrategy interpreter : interpreters) {
                if (interpreter.validate(sentence)) {
                    interpreter.interpret(sentence, types, values, result);
                    break;
                }
            }
        }
    }

    public Result getResult() {
        return result;
    }
}
