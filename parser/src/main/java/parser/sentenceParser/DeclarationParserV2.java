package parser.sentenceParser;

import org.austral.edu.TokenType;

public class DeclarationParserV2 extends DeclarationParser {

    public DeclarationParserV2() {
        super();
        super.pattern[0] = new TokenType[]{
                TokenType.LET, TokenType.CONST
        };
        super.pattern[3] = new TokenType[]{
                TokenType.NUMBER_TYPE, TokenType.STRING_TYPE, TokenType.BOOLEAN_TYPE
        };
    }
}
