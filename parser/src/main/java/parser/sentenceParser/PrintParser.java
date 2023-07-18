package parser.sentenceParser;

import ast.ExpressionNode;
import ast.Node;
import ast.PrintNode;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenTypeV1;

import java.util.List;

public class PrintParser extends AbstractParser {

    ExpressionParser expressionParser;

    public PrintParser(ExpressionParser expressionParser) {
        super(new TokenTypeV1[][]{
                {TokenTypeV1.PRINTLN},
                {TokenTypeV1.L_PAR},
        });
        this.expressionParser = expressionParser;
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        validate(sentence);
        return new PrintNode((ExpressionNode) expressionParser.parse(sentence.subList(2,sentence.size()-2)));
    }

    @Override
    public void hookValidate(List<Token> sentence) throws ExpectedTokenException {
        if (!sentence.get(sentence.size()-2).tokenType.equals(TokenTypeV1.R_PAR))
            throw new ExpectedTokenException(TokenTypeV1.R_PAR);
        super.hookValidate(sentence);
    }
}
