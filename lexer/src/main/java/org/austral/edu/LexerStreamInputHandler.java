package org.austral.edu;
import exceptions.UnclosedBracesException;
import exceptions.UnclosedParenthesesException;
import exceptions.UnclosedStringLiteralException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LexerStreamInputHandler {
    public List<Token> process(InputStream inputStream) throws IOException, UnclosedStringLiteralException, UnclosedParenthesesException, UnclosedBracesException {
        StringBuilder stringBuilder = new StringBuilder();
        int currentChar = inputStream.read();
        stringBuilder.append((char) currentChar);
        Lexer lexer = new LexerV1();
        List<Token> tokens = new ArrayList<>();

        while (currentChar != -1){
            while ( (char) currentChar != ';') {
                currentChar = inputStream.read();
                stringBuilder.append( (char) currentChar);
            }
            tokens.addAll(lexer.lex(new StringInput(stringBuilder.toString() )));
            currentChar = inputStream.read();
            stringBuilder = new StringBuilder();
        }
        inputStream.close();
        return tokens;
    }
}
