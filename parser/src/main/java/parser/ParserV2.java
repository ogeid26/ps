package parser;

import org.austral.edu.Token;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;
import parser.sentenceParser.*;

import java.util.ArrayList;
import java.util.List;

public class ParserV2 extends Parser {

    public ParserV2() {
        super(new SentenceParser[]{
                new DeclarationParserV2(),
                new AssignationParserV2(),
                new DeclareAndAssignParserV2(),
                new PrintParser(new ExpressionParserV2()),
                new IfParser()
        });
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
        return subLists;
    }
}
