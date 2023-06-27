package org.austral.edu;

import org.austral.edu.Nodes.DeclareNode;
import org.austral.edu.Nodes.NameNode;
import org.austral.edu.Nodes.Node;
import org.austral.edu.Nodes.TypeNode;

import java.util.ArrayList;
import java.util.Arrays;

public class DeclarationParserV2 extends DeclarationParser {

    DeclarationParserV2() {
        super();
        super.pattern[3] = new TokenType[]{TokenType.NUMBERTYPE, TokenType.STRINGTYPE, TokenType.BOOLEANTYPE};
    }
}
