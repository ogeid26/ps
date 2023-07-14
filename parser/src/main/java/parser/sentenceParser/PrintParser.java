package parser.sentenceParser;

import ast.ExpressionNode;
import ast.Node;
import ast.PrintNode;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;

import java.util.List;

public class PrintParser extends AbstractParser {

    public PrintParser() {
        super(new TokenType[][]{
                {TokenType.PRINTLN},
                {TokenType.L_PAR},
        });
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {
        return new PrintNode((ExpressionNode) new ExpressionParser().parse(sentence.subList(2,sentence.size()-2)));
    }

    @Override
    public boolean hookValidate(List<Token> sentence) {
        if (!sentence.get(sentence.size()-2).tokenType.equals(TokenType.R_PAR))
            return false;
        return sentence.get(sentence.size() - 1).tokenType.equals(TokenType.SEMICOLON);
    }
}
