package org.austral.edu;

import java.util.HashMap;

public class FunctionInterpreter implements InterpreterStrategy{
    @Override
    public boolean validate(AbstractSyntaxTree tree) {
        return tree.root.content.equals("Function");
    }

    @Override
    public void interpret(AbstractSyntaxTree tree, HashMap<String,String> types, HashMap<String,String> values) {
    }
}
