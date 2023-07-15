package ast;

import exceptions.DividedByZeroException;
import exceptions.IncompatibleOperationException;
import exceptions.VariableDoesntExistsException;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;

import java.util.Map;

public class DivisionNode extends ExpressionNode {

    public DivisionNode(ast.ExpressionNode left, ast.ExpressionNode right) {
        super("Division", left, right);
    }

    public Node solve(Map<String,String> values, Map<String,String> types) throws IncompatibleOperationException, DividedByZeroException, VariableDoesntExistsException {
        ValueNode valueLeft = (ValueNode) getLeft().solve(values, types);
        ValueNode valueRight = (ValueNode) getRight().solve(values, types);

        if (valueLeft.type.equals("number") && valueRight.type.equals("number")) {
            if (Double.parseDouble(valueRight.content) != 0) {
                double result = Double.parseDouble(valueLeft.content) / Double.parseDouble(valueRight.content);
                return new ValueNumberNode(String.valueOf(result));
            }
            throw new DividedByZeroException();
        }
        throw new IncompatibleOperationException(type, valueLeft.type, valueRight.content);
    }
}
