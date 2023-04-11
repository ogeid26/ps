package org.austral.edu;

import java.util.HashMap;

public class KeywordInterpreter implements InterpreterStrategy{
    @Override
    public boolean validate(AbstractSyntaxTree tree) {
        return (tree.root.children.get(0).content.equals("Assignation") || tree.root.children.get(0).content.equals("Declaration"));
    }

    @Override
    public void interpret(AbstractSyntaxTree tree, HashMap<String,String> types, HashMap<String,String> values) {
        if (tree.root.content.equals("Assignation")){
            types.put(tree.root.children.get(0).children.get(0).children.get(1).content,tree.root.children.get(0).children.get(0).children.get(0).content);
            values.put(tree.root.children.get(0).children.get(0).children.get(1).content,tree.root.children.get(0).children.get(1).children.get(0).content);
            if (types.get(tree.root.children.get(0).children.get(0).children.get(1).content).equals("String") && values.get(tree.root.children.get(0).children.get(0).children.get(1).content).contains("'")){

            } else if (types.get(tree.root.children.get(0).children.get(0).children.get(1).content).equals("Number") && !values.get(tree.root.children.get(0).children.get(0).children.get(1).content).contains("'")) {

            }else{
                throw new RuntimeException("Incompatible type and value");
            }
        }else {
            types.put(tree.root.children.get(0).children.get(0).children.get(1).content, tree.root.children.get(0).children.get(0).children.get(0).content);
        }
    }
}
