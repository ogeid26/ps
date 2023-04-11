package org.austral.edu;

import java.util.HashMap;

public interface InterpreterStrategy {
    boolean validate(AbstractSyntaxTree tree);

    void interpret(AbstractSyntaxTree tree, HashMap<String,String> types, HashMap<String,String> values);
}
