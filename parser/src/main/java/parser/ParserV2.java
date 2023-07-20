package parser;

import ast.Node;
import exceptions.ExpectedTokenException;
import exceptions.UnexpectedTokenException;
import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;
import parser.sentenceParser.*;

import java.util.ArrayList;
import java.util.List;

public class ParserV2 extends Parser {

    public Node parseSentence(List<Token> sentence) throws UnexpectedTokenException, ExpectedTokenException {
        TokenType type = sentence.get(0).tokenType;
        if (type.equals(TokenTypeV1.LET) || type.equals(TokenTypeV2.CONST))
            return (new DeclarationParserV2()).parse(sentence);
        if (type.equals(TokenTypeV1.IDENTIFIER))
            return (new AssignationParserV2()).parse(sentence);
        if (type.equals(TokenTypeV1.PRINTLN))
            return (new PrintParser(new ExpressionParserV2())).parse(sentence);
        if (type.equals(TokenTypeV2.IF))
            return (new IfParser()).parse(sentence);
        throw new UnexpectedTokenException(sentence.get(0));
    }

    @Override
    public List<List<Token>> splitBySentences(List<Token> list) {
        boolean openBraces = false;
        boolean recentCloses = false;
        List<List<Token>> subLists = new ArrayList<>();
        List<Token> subList = new ArrayList<>();
        for (Token token : list) {
            if (recentCloses && !token.tokenType.equals(TokenTypeV2.ELSE)) {
                subLists.add(subList);
                subList = new ArrayList<>();
            }
            recentCloses = false;
            subList.add(token);
            if (token.tokenType.equals(TokenTypeV1.SEMICOLON) && !openBraces) {
                subLists.add(subList);
                subList = new ArrayList<>();
                continue;
            }
            if (token.tokenType.equals(TokenTypeV2.L_BRACES)) {
                openBraces = true;
                continue;
            }
            if (token.tokenType.equals(TokenTypeV2.R_BRACES)) {
                openBraces = false;
                recentCloses = true;
            }
        }
        if (recentCloses)
            subLists.add(subList);
            subList = new ArrayList<>();
        if (!subList.isEmpty())
            subLists.add(subList);
        return subLists;
    }
}
