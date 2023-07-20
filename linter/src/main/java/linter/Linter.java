package linter;

import exceptions.*;
import org.austral.edu.FileInput;
import org.austral.edu.LexerImpl;

public class Linter extends LexerImpl {
    private final String pathFile;

    public Linter(String pathFile, TokenizerAnalyzer tokenizer) {
        super(tokenizer);
        this.pathFile = pathFile;
    }

    public void lint() throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {

        lex(new FileInput(pathFile));
    }
}
