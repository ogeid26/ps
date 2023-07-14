package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

public class AdditionNode extends ExpressionNode {

    public AdditionNode(ExpressionNode left, ExpressionNode right) {
        super("Sum", left, right);
    }

    public Node solve() throws IncompatibleOperationException, DividedByZeroException {
        ValueNode valueLeft = (ValueNode) getLeft().solve();
        ValueNode valueRight = (ValueNode) getRight().solve();

        if (valueLeft.type.equals("Number") && valueRight.type.equals("Number")) {
            double result = Double.parseDouble(valueLeft.content) + Double.parseDouble(valueRight.content);
            return new ValueNumberNode(String.valueOf(result));
        }

        return new ValueStringNode(valueLeft.content + valueRight.content);
    }
}
