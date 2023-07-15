package parser.sentenceParser;

import ast.AssignDeclareNode;
import ast.DeclareNode;
import ast.Node;
import ast.ExpressionNode;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;

import java.util.List;

public class DeclareAndAssignParser extends AbstractParser {

    public DeclareAndAssignParser() {
        super(new TokenType[][]{
                {TokenTypeV1.LET},
                {TokenTypeV1.IDENTIFIER},
                {TokenTypeV1.DECLARATION},
                {TokenTypeV1.NUMBER_TYPE, TokenTypeV1.STRING_TYPE},
                {TokenTypeV1.ASSIGNATION}
        });
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {
        return new AssignDeclareNode(
                (DeclareNode) new DeclarationParser().parse(sentence.subList(0,4)),
                (ExpressionNode) new ExpressionParser().parse(sentence.subList(5,sentence.size()-1))
        );
    }

    @Override
    public boolean hookValidate(List<Token> sentence) {
        return sentence.get(sentence.size()-1).tokenType.equals(TokenTypeV1.SEMICOLON);
    }
}
