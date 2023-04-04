import java.util.ArrayList;
import java.util.Arrays;

public class LexerImpl implements Lexer{
    @Override
    public ArrayList<Token> lex(String input) {
        String[] words = input.split("  *");
        System.out.println(Arrays.toString(words));
        return new ArrayList<Token>();
    }



}
