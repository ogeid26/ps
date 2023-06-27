package org.austral.edu;


import java.util.ArrayList;

public class ParserV2 extends Parser {

    ParserV2() {
        super(new SentenceParser[]{
                new DeclarationParserV2(),
                new AssignationParser(),
                new DeclareAndAssignParser()
        });
    }
}
