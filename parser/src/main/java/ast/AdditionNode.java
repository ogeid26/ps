package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

import java.util.Map;

public class AdditionNode extends ExpressionNode {

    public AdditionNode(ExpressionNode left, ExpressionNode right) {
        super("Sum", left, right);
    }

    public Node solve(Map<String,String> values, Map<String,String> types) throws IncompatibleOperationException, DividedByZeroException, VariableDoesntExistsException {
        ValueNode valueLeft = (ValueNode) getLeft().solve(values, types);
        ValueNode valueRight = (ValueNode) getRight().solve(values, types);

        if (valueLeft.type.equals("Number") && valueRight.type.equals("Number")) {
            double result = Double.parseDouble(valueLeft.content) + Double.parseDouble(valueRight.content);
            return new ValueNumberNode(String.valueOf(result));
        }

        return new ValueStringNode(valueLeft.content + valueRight.content);
    }
}
