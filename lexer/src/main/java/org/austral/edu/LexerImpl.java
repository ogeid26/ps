package org.austral.edu;

import exceptions.UnclosedParenthesesException;
import exceptions.UnclosedStringLiteralException;

import java.util.ArrayList;
import java.util.List;

public class LexerImpl implements Lexer {
    public InputProvider provider = new StringInput();
    public Tokenizer tokenizer;

    @Override
    public List<Token> lex(InputProvider provider) throws
            UnclosedStringLiteralException, UnclosedParenthesesException {
        String input = provider.getContent();
        List<Token> tokens = new ArrayList<>();
        tokenizer = new TokenizerImpl();

        int length = input.length();

        for (int i = 0, col = 0, row = 0; i < length; i++) {
            char currentChar = input.charAt(i);
            col++;

            StringBuilder currentString = new StringBuilder();
            currentString.append(currentChar);
            while (i < length - 1) {
                if (currentChar == '"' || currentChar == '\'') {
                    int nextQuoteMark = getNextQuoteMark(input, currentChar, i);
                    if (nextQuoteMark == -1)
                        throw new UnclosedStringLiteralException(col, row);
                    currentString.append(input, i + 1, i + nextQuoteMark + 2);
                    i = i + nextQuoteMark + 1;
                    col = col + nextQuoteMark + 1;
                    break;
                }

                if (currentChar == '(') {
                    if (!checkNextParentheses(input, i))
                        throw new UnclosedParenthesesException(col, row);
                    break;
                }

                if (
                        !Character.isLetterOrDigit(currentChar)
                                && currentChar != '.'
                                && currentChar != '_'
                        // TODO Se puede flexibilizar conteniendolo en una lista que se le pasa al lexer.
                )
                    break;

                char nextChar = input.charAt(i + 1);

                if (Character.isLetterOrDigit(nextChar) || nextChar == '.' || nextChar == '_') {
                    currentString.append(nextChar);
                    currentChar = nextChar;
                    col++;
                    i++;
                } else break;
            }
            if (currentChar == '\n') {
                col = 0;
                row++;
            }

            if (currentString.length() != 0 && currentString.charAt(0) > 32) {
                tokens.add(
                        tokenizer.tokenize(
                                currentString.toString(),
                                i - currentString.length() + 1,
                                col - currentString.length(),
                                col,
                                row
                                // TODO Revisar ante una nueva línea
                        )
                );
            }
        }

        return tokens;
    }

    private int getNextQuoteMark(String input, char currentChar, int i) {
        // TODO Manejar que no exista la otra comilla y que no se mezclen
        return currentChar == '"'
                ? (input.substring(i + 1)).indexOf('"')
                : (input.substring(i + 1)).indexOf('\'');
    }

    private boolean checkNextParentheses(String input, int i) {
        return (input.indexOf(')') != -1);
    }
}


