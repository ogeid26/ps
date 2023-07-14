package parser.sentenceParser;

import ast.AssignNode;
import ast.IdentifierNode;
import ast.Node;
import ast.ExpressionNode;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;

import java.util.List;

public class AssignationParser extends AbstractParser {

    public AssignationParser() {
        super(new TokenType[][]{
                {TokenType.IDENTIFIER},
                {TokenType.ASSIGNATION}
        });
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {
        return new AssignNode(
                new IdentifierNode(sentence.get(0).content),
                (ExpressionNode) new ExpressionParser().parse(sentence.subList(2,sentence.size()-1))
        );
    }

    @Override
    public boolean hookValidate(List<Token> sentence) {
        return sentence.get(sentence.size()-1).tokenType.equals(TokenType.SEMICOLON);
    }
}
