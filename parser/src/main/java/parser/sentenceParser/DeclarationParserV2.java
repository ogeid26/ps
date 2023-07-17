package parser.sentenceParser;

import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenTypeV2;

public class DeclarationParserV2 extends DeclarationParser {

    public DeclarationParserV2() {
        super.pattern[0] = new TokenType[]{
                TokenTypeV1.LET, TokenTypeV2.CONST
        };
        super.pattern[3] = new TokenType[]{
                TokenTypeV1.NUMBER_TYPE, TokenTypeV1.STRING_TYPE, TokenTypeV2.BOOLEAN_TYPE
        };
    }
}
