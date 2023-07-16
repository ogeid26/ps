package org.austral.edu;

import ast.AbstractSyntaxTree;
import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InterpreterV1 {
    HashMap<String,String> types;
    HashMap<String,String> values;
    
    Result result;

    List<InterpreterStrategyV1> strategies = Arrays.asList(new PrintInterpreterV1(), new IdentifierInterpreterV1(), new KeywordInterpreterV1());

    public InterpreterV1(Result result){
         this.types = new HashMap<>();
         this.values = new HashMap<>();
         this.result = result;
    }

    public void interpret(AbstractSyntaxTree ast) throws AssignationException, IncompatibilityException, NotDefinedException, InterpretException, DividedByZeroException, IncompatibleOperationException, VariableDoesntExistsException {
        for (Node sentence: ast.getChildren()) {
            for (InterpreterStrategyV1 interpreter : strategies) {
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
