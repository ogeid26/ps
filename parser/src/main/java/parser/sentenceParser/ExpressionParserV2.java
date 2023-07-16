package parser.sentenceParser;

import ast.*;
import org.austral.edu.Nodes.ValueBooleanNode;
import org.austral.edu.Token;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;

public class ExpressionParserV2 extends ExpressionParser {

    @Override
    protected ExpressionNode getLiteralNode(Token current) {
        if (current.tokenType instanceof TokenTypeV1)
            return super.getLiteralNode(current);
        return current.tokenType.equals(TokenTypeV2.BOOLEAN)?
                new LiteralNode(new ValueBooleanNode(current.content)) : null;
    }
}
