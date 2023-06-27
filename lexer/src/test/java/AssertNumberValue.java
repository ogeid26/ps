import org.austral.edu.Lexer;
import org.austral.edu.LexerImpl;
import org.austral.edu.Token;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertNumberValue {
    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    public void testLexer() {
        Lexer lexer = new LexerImpl();
        ArrayList<Token> tokens =  lexer.lex("let x: Number = 19;");

        for (Token token: tokens) {
            System.out.println(token.tokenType);
        }

        assertEquals(7, tokens.size());
    }
}
