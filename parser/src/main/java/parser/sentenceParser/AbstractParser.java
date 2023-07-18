package parser.sentenceParser;

import ast.Node;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractParser implements SentenceParser {

    TokenType[][] pattern;

    AbstractParser(TokenType[][] pattern) {
        this.pattern = pattern;
    }

    @Override
    public void validate(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        if (sentence.size() < pattern.length)
            throw new UnexpectedTokenException(sentence.get(0));
        for (int i = 0; i < pattern.length; i++) {
            if (!Arrays.asList(pattern[i]).contains(sentence.get(i).tokenType))
                throw new ExpectedTokenException(pattern[i][0]);
        }
        hookValidate(sentence);
    }

    @Override
    public abstract Node parse(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException;

    public void hookValidate(List<Token> sentence) throws ExpectedTokenException {
        if (!sentence.get(sentence.size()-1).tokenType.equals(TokenTypeV1.SEMICOLON))
            throw new ExpectedTokenException(TokenTypeV1.SEMICOLON);
    }
}
