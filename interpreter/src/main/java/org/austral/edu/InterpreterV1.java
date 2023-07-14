package org.austral.edu;

import org.austral.edu.Results.Result;

public class InterpreterV1 extends Interpreter {

    public InterpreterV1(Result result) {
        super(result, new InterpreterStrategy[]{
                new PrintInterpreter(),
                new IdentifierInterpreter(),
                new KeywordInterpreter()
        });
    }
}
