package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;

public class MultiplicationNode extends ExpressionNode {

    public MultiplicationNode(ExpressionNode left, ExpressionNode right) {
        super("Multiplication", left, right);
    }

    public Node solve() throws IncompatibleOperationException, DividedByZeroException {
        ValueNode valueLeft = (ValueNode) getLeft().solve();
        ValueNode valueRight = (ValueNode) getRight().solve();

        if (valueLeft.type.equals("Number") && valueRight.type.equals("Number")) {
            double result = Double.parseDouble(valueLeft.content) * Double.parseDouble(valueRight.content);
            return new ValueNumberNode(String.valueOf(result));
        }

        throw new IncompatibleOperationException(type, valueLeft.type, valueRight.content);
    }
}
