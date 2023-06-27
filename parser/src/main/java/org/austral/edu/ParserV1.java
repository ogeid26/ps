package org.austral.edu;

public class ParserV1 extends Parser {
    ParserV1() {
        super(new SentenceParser[]{
                new DeclarationParser(),
                new AssignationParser(),
                new DeclareAndAssignParser()
        });
    }
}
