package parser.sentenceParser;

import ast.*;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;

import java.util.Arrays;
import java.util.List;

public class DeclareAndAssignParserV2 extends AbstractParser {

    public boolean readInput;
    public TokenType[][] optionalPattern;

    public DeclareAndAssignParserV2() {
        super(new TokenType[][]{
                {TokenTypeV1.LET, TokenTypeV2.CONST},
                {TokenTypeV1.IDENTIFIER},
                {TokenTypeV1.DECLARATION},
                {TokenTypeV1.STRING_TYPE, TokenTypeV1.NUMBER_TYPE, TokenTypeV2.BOOLEAN_TYPE},
                {TokenTypeV1.ASSIGNATION},
        });
        readInput = false;
        optionalPattern = new TokenType[][]{
                {TokenTypeV2.READ_INPUT},
                {TokenTypeV1.L_PAR},
                {TokenTypeV1.STRING},
                {TokenTypeV1.R_PAR}
        };
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {
        DeclareNode declareNode = (DeclareNode) new DeclarationParser().parse(sentence.subList(0,4));
        if (readInput) {
            return new DeclareReaderNode(
                    declareNode,
                    new ReaderNode(sentence.get(7).content)
            );
        }
        return new AssignDeclareNode(
                declareNode,
                (ExpressionNode) new ExpressionParserV2().parse(sentence.subList(5,sentence.size()-1))
        );
    }

    @Override
    public boolean hookValidate(List<Token> sentence) {
        readInput = true;
        for (int i = 0; i < optionalPattern.length; i++) {
            if (!Arrays.asList(optionalPattern[i]).contains(sentence.get(i + 5).tokenType)) {
                readInput = false;
                break;
            }
        }
        return sentence.get(sentence.size()-1).tokenType.equals(TokenTypeV1.SEMICOLON);
    }
}
