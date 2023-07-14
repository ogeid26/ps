package parser.sentenceParser;

import ast.*;
import org.austral.edu.Token;
import org.austral.edu.TokenType;

import java.util.List;

public class DeclarationParser extends AbstractParser {

    public DeclarationParser() {
        super(new TokenType[][]{
                {TokenType.LET},
                {TokenType.IDENTIFIER},
                {TokenType.DECLARATION},
                {TokenType.NUMBER_TYPE, TokenType.STRING_TYPE},
                {TokenType.SEMICOLON}
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
