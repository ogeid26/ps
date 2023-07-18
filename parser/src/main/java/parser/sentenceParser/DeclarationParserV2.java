package parser.sentenceParser;

import ast.*;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;

import java.util.Arrays;
import java.util.List;

public class DeclarationParserV2 extends DeclarationParser {

    public boolean readInput;
    public TokenType[][] optionalPattern;

    public DeclarationParserV2() {
        super.pattern[0] = new TokenType[]{
                TokenTypeV1.LET, TokenTypeV2.CONST
        };
        super.pattern[3] = new TokenType[]{
                TokenTypeV1.NUMBER_TYPE, TokenTypeV1.STRING_TYPE, TokenTypeV2.BOOLEAN_TYPE
        };
        readInput = false;
        optionalPattern = new TokenType[][]{
                {TokenTypeV2.READ_INPUT},
                {TokenTypeV1.L_PAR},
                {TokenTypeV1.STRING},
                {TokenTypeV1.R_PAR}
        };
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        validate(sentence);
        DeclareNode declareNode = new DeclareNode(
                new TypeNode(sentence.get(3).tokenType.getName()),
                new IdentifierNode(sentence.get(1).content),
                new KeywordNode(sentence.get(0).tokenType.getName())
        );
        if (readInput) {
            return new DeclareReaderNode(
                    declareNode,
                    new ReaderNode(sentence.get(7).content)
            );
        }
        if (sentence.get(4).tokenType.equals(TokenTypeV1.SEMICOLON))
            return declareNode;
        if (sentence.get(4).tokenType.equals(TokenTypeV1.ASSIGNATION))
            return new AssignDeclareNode(
                    declareNode,
                    (ExpressionNode) new ExpressionParserV2().parse(sentence.subList(5,sentence.size()-1))
            );
        throw new UnexpectedTokenException(sentence.get(4));
    }

    @Override
    public void hookValidate(List<Token> sentence) throws ExpectedTokenException {
        readInput = sentence.size() > 9;
        for (int i = 0; i < optionalPattern.length && sentence.size() > 9; i++) {
            if (!Arrays.asList(optionalPattern[i]).contains(sentence.get(i + 5).tokenType)) {
                readInput = false;
                break;
            }
        }
        super.hookValidate(sentence);
    }
}
