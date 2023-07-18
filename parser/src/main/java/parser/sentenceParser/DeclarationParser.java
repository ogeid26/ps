package parser.sentenceParser;

import ast.*;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;

import java.util.List;

public class DeclarationParser extends AbstractParser {

    public DeclarationParser() {
        super(new TokenType[][]{
                {TokenTypeV1.LET},
                {TokenTypeV1.IDENTIFIER},
                {TokenTypeV1.DECLARATION},
                {TokenTypeV1.NUMBER_TYPE, TokenTypeV1.STRING_TYPE},
        });
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        validate(sentence);
        DeclareNode declareNode = new DeclareNode(
                new TypeNode(sentence.get(3).tokenType.getName()),
                new IdentifierNode(sentence.get(1).content),
                new KeywordNode(sentence.get(0).tokenType.getName())
        );
        if (sentence.get(4).tokenType.equals(TokenTypeV1.SEMICOLON))
            return declareNode;
        if (sentence.get(4).tokenType.equals(TokenTypeV1.ASSIGNATION))
            return new AssignDeclareNode(
                    declareNode,
                    (ExpressionNode) new ExpressionParser().parse(sentence.subList(5,sentence.size()-1))
            );
        throw new UnexpectedTokenException(sentence.get(4));
    }
}
