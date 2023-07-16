package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;

import java.util.Map;

public class MultiplicationNode extends ExpressionNode {

    public MultiplicationNode(ExpressionNode left, ExpressionNode right) {
        super("Multiplication", left, right);
    }

    public Node solve(Map<String,String> values, Map<String,String> types) throws IncompatibleOperationException, DividedByZeroException, VariableDoesntExistsException {
        ValueNode valueLeft = (ValueNode) getLeft().solve(values, types);
        ValueNode valueRight = (ValueNode) getRight().solve(values, types);

        if (valueLeft.type.equals("number") && valueRight.type.equals("number")) {
            double result = Double.parseDouble(valueLeft.content) * Double.parseDouble(valueRight.content);
            return new ValueNumberNode(String.valueOf(result));
        }

        throw new IncompatibleOperationException(type, valueLeft.type, valueRight.content);
    }
}
