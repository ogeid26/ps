package ast;

import exceptions.VariableDoesntExistsException;
import org.austral.edu.Nodes.ValueBooleanNode;
import org.austral.edu.Nodes.ValueNode;
import org.austral.edu.Nodes.ValueNumberNode;
import org.austral.edu.Nodes.ValueStringNode;

import java.util.Map;

public class LiteralNode extends ExpressionNode {

    public final ValueNode value;

    public LiteralNode(ValueNode value) {
        super("Literal");
        this.value = value;
    }

    public Node solve(Map<String,String> values, Map<String,String> types) throws VariableDoesntExistsException {
        if (value.type.equals("Variable")) {
            if (values.containsKey(value.getContent())){
                String type = types.get(value.getContent());
                switch (type) {
                    case "number":
                        return new ValueNumberNode(values.get(value.getContent()));
                    case "string":
                        return new ValueStringNode(values.get(value.getContent()));
                    case "boolean":
                        return new ValueBooleanNode(values.get(value.getContent()));
                    default:
                        return null;
                }
            }else{
                throw new VariableDoesntExistsException(value.getContent());
            }
        }
        return value;
    }
}
