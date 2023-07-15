package parser.sentenceParser;

import ast.AbstractSyntaxTree;
import ast.Node;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Nodes.IfNode;
import org.austral.edu.Nodes.ValueBooleanNode;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;
import parser.ParserV2;

import java.util.List;

public class IfParser extends AbstractParser {

    public IfParser() {
        super(new TokenType[][]{
                {TokenTypeV2.IF},
                {TokenTypeV1.L_PAR},
                {TokenTypeV1.IDENTIFIER, TokenTypeV2.BOOLEAN},
                {TokenTypeV1.R_PAR},
                {TokenTypeV2.L_BRACES}
        });
    }

    @Override
    public Node parse(List<Token> sentence) throws UnexpectedTokenException {
        int elseIndex = getElseIndex(sentence);
        AbstractSyntaxTree trueTree = new ParserV2().parse(sentence.subList(5,elseIndex-1));
        AbstractSyntaxTree falseTree = new AbstractSyntaxTree();
        if (elseIndex > 0)
            falseTree = new ParserV2().parse(sentence.subList(elseIndex+2, sentence.size()-1));
        return new IfNode(sentence.get(2).content, trueTree, falseTree);
    }

    private int getElseIndex(List<Token> sentence) {
        for (int i = 0; i < sentence.size(); i++) {
            if (sentence.get(i).tokenType.equals(TokenTypeV2.ELSE))
                return i;
        } return -1;
    }
}
