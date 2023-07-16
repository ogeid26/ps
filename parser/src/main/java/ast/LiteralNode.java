package ast;

import exceptions.VariableDoesntExistsException;
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
                return (switch (types.get(value.getContent())) {
                    case "number" -> new ValueNumberNode(values.get(value.getContent()));
                    case "string" -> new ValueStringNode(values.get(value.getContent()));
                    default -> null;
                });
            }else{
                throw new VariableDoesntExistsException(value.getContent());
            }
        }
        return value;
    }
}
