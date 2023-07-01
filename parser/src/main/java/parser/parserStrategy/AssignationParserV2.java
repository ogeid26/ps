package parser.parserStrategy;

import org.austral.edu.TokenType;

public class AssignationParserV2 extends AssignationParser {

    AssignationParserV2() {
        super();
        super.pattern[2] = new TokenType[]{
                TokenType.NUMBER,
                TokenType.STRING,
                TokenType.IDENTIFIER,
                TokenType.BOOLEAN,
                TokenType.READ_INPUT
        };
    }
}
