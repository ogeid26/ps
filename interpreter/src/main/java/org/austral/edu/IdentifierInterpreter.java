package org.austral.edu;

import java.util.HashMap;

public class IdentifierInterpreter implements InterpreterStrategy{
    @Override
    public boolean validate(AbstractSyntaxTree tree) {
        return tree.root.content.equals("Assignation");
    }

    @Override
    public void interpret(AbstractSyntaxTree tree, HashMap<String,String> types, HashMap<String,String> values) {
        if (types.isEmpty()){
            throw new RuntimeException("Your variable has not been defined");
        }else{
            values.put(tree.root.children.get(0).children.get(0).content,tree.root.children.get(0).children.get(1).children.get(0).content);
            if (types.get(tree.root.children.get(0).children.get(0).content).equals("String") && values.get(tree.root.children.get(0).children.get(0).content).contains("'")){
                System.out.println("Success");
            } else if (types.get(tree.root.children.get(0).children.get(0).content).equals("Number") && !values.get(tree.root.children.get(0).children.get(0).content).contains("'")) {
                System.out.println("Success");
            }else{
                throw new RuntimeException("Incompatible type and value");
            }
        }
    }
}
