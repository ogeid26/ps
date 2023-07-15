package parser;

import parser.sentenceParser.*;

public class ParserV1 extends Parser {

    public ParserV1() {
        super(new SentenceParser[]{
                new DeclarationParser(),
                new AssignationParser(),
                new DeclareAndAssignParser(),
                new PrintParser(new ExpressionParser())
        });
    }
}
