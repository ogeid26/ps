package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;

public class DivisionNode extends ExpressionNode {

    public DivisionNode(ast.ExpressionNode left, ast.ExpressionNode right) {
        super("Division", left, right);
    }

    public Node solve() throws IncompatibleOperationException, DividedByZeroException {
        ValueNode valueLeft = (ValueNode) getLeft().solve();
        ValueNode valueRight = (ValueNode) getRight().solve();

        if (valueLeft.type.equals("Number") && valueRight.type.equals("Number")) {
            if (Double.parseDouble(valueRight.content) != 0) {
                double result = Double.parseDouble(valueLeft.content) / Double.parseDouble(valueRight.content);
                return new ValueNumberNode(String.valueOf(result));
            }
            throw new DividedByZeroException();
        }
        throw new IncompatibleOperationException(type, valueLeft.type, valueRight.content);
    }
}
