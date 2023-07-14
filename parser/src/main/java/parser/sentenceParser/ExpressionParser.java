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
        Stack<ExpressionNode> nodes = new Stack<>();
        Stack<Token> operands = new Stack<>();
        boolean literalRequired = true;
        boolean priorityOperand = false;

        for (int i = 0; i < sentence.size(); i++) {
            Token current = sentence.get(i);
            if (literalRequired) {
                ExpressionNode node = getLiteralNode(current);
                if (node == null) {
                    if (current.tokenType.equals(TokenType.L_PAR)) {
                        int next_par = findNextParenthesis(sentence, i);
                        node = (ExpressionNode) parse(sentence.subList(i + 1, next_par));
                        i = next_par;
                    } else {
                        throw new UnexpectedTokenException(current);
                    }
                }
                if (priorityOperand) {
                    node = getExpressionNode(operands.pop(), nodes.pop(), node);
                    priorityOperand = false;
                }
                nodes.push(node);
            } else {
                if (!isOperand(current))
                    throw new UnexpectedTokenException(current);
                priorityOperand = hasPriority(current);
                operands.push(current);
            }
            literalRequired = !literalRequired;
        }
        if (literalRequired)
            throw new UnexpectedTokenException(operands.pop());
        while (!operands.empty()) {
            ExpressionNode last = nodes.pop();
            nodes.push(getExpressionNode(operands.pop(), nodes.pop(), last));
        }

        return nodes.pop();
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

    public boolean isOperand(Token token) {
        return List.of(TokenType.PLUS, TokenType.MINUS, TokenType.MULTIPLY, TokenType.DIVIDE).
                contains(token.tokenType);
    }

    public boolean hasPriority(Token token) {
        return List.of(TokenType.MULTIPLY, TokenType.DIVIDE).contains(token.tokenType);
    }
}
