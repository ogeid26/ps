package parser;

import ast.Node;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import parser.sentenceParser.*;

import java.util.List;

public class ParserV1 extends Parser {

    public Node parseSentence(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        TokenType type = sentence.get(0).tokenType;
        if (type.equals(TokenTypeV1.LET))
            return (new DeclarationParser()).parse(sentence);
        if (type.equals(TokenTypeV1.IDENTIFIER))
            return (new AssignationParser()).parse(sentence);
        if (type.equals(TokenTypeV1.PRINTLN))
            return (new PrintParser(new ExpressionParser())).parse(sentence);
        throw new UnexpectedTokenException(sentence.get(0));
    }
}
