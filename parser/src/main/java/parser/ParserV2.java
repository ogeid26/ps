package parser;

import parser.sentenceParser.AssignationParser;
import parser.sentenceParser.DeclarationParserV2;
import parser.sentenceParser.DeclareAndAssignParser;
import parser.sentenceParser.SentenceParser;

public class ParserV2 extends Parser {

    public ParserV2() {
        super(new SentenceParser[]{
                new DeclarationParserV2(),
                new AssignationParser(),
                new DeclareAndAssignParser()
        });
    }
}
