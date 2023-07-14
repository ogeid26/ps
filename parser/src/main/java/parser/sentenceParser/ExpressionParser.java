package parser.sentenceParser;

import ast.*;
import ast.ExpressionNode;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Nodes.*;
import org.austral.edu.Token;
import org.austral.edu.TokenType;

import java.util.List;
import java.util.Stack;

public class ExpressionParser extends AbstractParser {

    public ExpressionParser() {
        super(new TokenType[][]{});
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {

        ExpressionNode left = null;
        ExpressionNode right = null;
        Token operation = null;

        for (int i = 0; i < sentence.size(); i++) {
            Token current = sentence.get(i);
            if (left == null) {
                left = getLiteralNode(current);
            } else {
                if (operation == null) {
                    operation = switch (current.tokenType) {
                        case PLUS, MINUS, MULTIPLY, DIVIDE -> current;
                        default -> throw new UnexpectedTokenException(current.tokenType.getName());
                    };
                } else {
                    if (right == null) {
                        right = getLiteralNode(current);
                        if (right == null) {
                            if (current.tokenType.equals(TokenType.L_PAR)) {
                                int next_par = findNextParenthesis(sentence, i);
                                right = (ExpressionNode) parse(sentence.subList(i + 1, next_par));
                            } else {
                                throw new UnexpectedTokenException(current.tokenType.getName());
                            }
                        }
                        continue;
                    }
                }
            }
            if (left != null && right != null) {
                Token sndOperation = switch (current.tokenType) {
                    case PLUS, MINUS, MULTIPLY, DIVIDE -> current;
                    default -> throw new UnexpectedTokenException(current.tokenType.getName());
                };
                if (List.of(TokenType.MULTIPLY,TokenType.DIVIDE).contains(operation.tokenType)) {
                    left = getExpressionNode(operation, left, right);
                    return getExpressionNode(sndOperation, left, (ExpressionNode) parse(sentence.subList(i+1,sentence.size())));
                } else {
                    right = getExpressionNode(sndOperation, right, (ExpressionNode) parse(sentence.subList(i+1,sentence.size())));
                    i = sentence.size();
                }
            }

            if (left != null) continue;

            if (current.tokenType.equals(TokenType.L_PAR)) {
                int next_par = findNextParenthesis(sentence, i);
                left = (ExpressionNode) parse(sentence.subList(i+1, next_par));
                i = next_par;
            } else {
                throw new UnexpectedTokenException(current.tokenType.getName());
            }
        }

        if (right != null) {
            return getExpressionNode(operation, left, right);
        }

        return left;
    }

    private ExpressionNode getLiteralNode(Token current) {
        return switch (current.tokenType) {
            case NUMBER -> new LiteralNode(new ValueNumberNode(current.content));
            case STRING -> new LiteralNode(new ValueStringNode(current.content));
            case IDENTIFIER -> new LiteralNode(new ValueVariableNode(current.content));
            default -> null;
        };
    }

    public int findNextParenthesis(List<Token> list, int index) {
        int l_counter = 0;
        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(i).tokenType.equals(TokenType.L_PAR)) {
                l_counter += 1;
                continue;
            }
            if (list.get(i).tokenType.equals(TokenType.R_PAR))
                if (l_counter > 0) {
                    l_counter -= 1;
                } else {
                    return i;
                }
        }
        return -1;
    }

    public ExpressionNode getExpressionNode(Token token, ExpressionNode left, ExpressionNode right) {
        return switch (token.tokenType) {
            case PLUS -> new AdditionNode(left, right);
            case MINUS -> new SubtractionNode(left, right);
            case MULTIPLY -> new MultiplicationNode(left, right);
            case DIVIDE -> new DivisionNode(left, right);
            default -> null;
        };
    }
}
