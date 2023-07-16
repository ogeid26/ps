package parser.sentenceParser;

import ast.Node;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParser implements SentenceParser {

    TokenType[][] pattern;

    AbstractParser(TokenType[][] pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean validate(List<Token> sentence) {
        if (sentence.size() < pattern.length)
            return false;
        for (int i = 0; i < pattern.length; i++) {
            if (!Arrays.asList(pattern[i]).contains(sentence.get(i).tokenType))
                return false;
        }
        return hookValidate(sentence);
    }

    @Override
    public abstract Node parse(List<Token> sentence) throws UnexpectedTokenException;

    public boolean hookValidate(List<Token> sentence) {
        return !sentence.isEmpty();
    }
}
