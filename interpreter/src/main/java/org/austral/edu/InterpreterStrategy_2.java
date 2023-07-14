package org.austral.edu;

import org.austral.edu.Exceptions.*;
import ast.Node;
import org.austral.edu.Results.Result;

import java.util.ArrayList;
import java.util.HashMap;

public interface InterpreterStrategy_2 {
    boolean validate(Node node);

    void interpret(Node node, HashMap<String,String> types, HashMap<String,String> values, ArrayList<String> constants, Result result) throws InterpretException, IncompatibilityException, NotDefinedException, ConstantVariableException, IllogicalConditionalException, AssignationException;
}
