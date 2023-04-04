import java.util.ArrayList;

public interface Lexer {
    // El lexer solo lexea
    ArrayList<Token> lex(String input);
}