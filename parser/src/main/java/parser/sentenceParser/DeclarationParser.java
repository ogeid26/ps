package parser.sentenceParser;

import ast.*;
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
                {TokenTypeV1.SEMICOLON}
        });
    }

    @Override
    public Node parse(List<Token> sentence) {
        return new DeclareNode(
                new TypeNode(sentence.get(3).tokenType.getName()),
                new IdentifierNode(sentence.get(1).content),
                new KeywordNode(sentence.get(0).tokenType.getName())
        );
    }
}
