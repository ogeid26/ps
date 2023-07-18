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

public class AssignationParserV2 extends AssignationParser {

    public boolean readInput;
    public TokenType[][] optionalPattern;

    public AssignationParserV2() {
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
        IdentifierNode identifierNode = new IdentifierNode(sentence.get(0).content);
        if (readInput) {
            return new AssignReaderNode(
                    identifierNode,
                    new ReaderNode(sentence.get(4).content)
            );
        }
        return new AssignNode(
                identifierNode,
                (ExpressionNode) new ExpressionParserV2().parse(sentence.subList(2,sentence.size()-1))
        );
    }

    @Override
    public void hookValidate(List<Token> sentence) throws ExpectedTokenException {
        readInput = true;
        for (int i = 0; i < optionalPattern.length; i++) {
            if (!Arrays.asList(optionalPattern[i]).contains(sentence.get(i + 2).tokenType)) {
                readInput = false;
                break;
            }
        }
        super.hookValidate(sentence);
    }
}
