import java.util.ArrayList;
public class LexerImpl implements Lexer{
    @Override
    public ArrayList<Token> lex(String input) {
        ArrayList<Token> tokens = new ArrayList<>();
        int length = input.length();
        // Vamos a ir armando los tokens en esta lista

        // Necesitamos un 'puntero' para saber a dónde vamos
        for (int i = 0, col = 0, row = 0; i < length; i++ ){
            char current = input.charAt(i);
            col++;

            // Iniciamos los ifs para ir separando
            while (i < length){
                if (!Character.isLetterOrDigit(current)
                )
                    break;
            }
        }
        return tokens;
    }



}
