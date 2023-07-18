package formatter;

import org.austral.edu.Token;
import org.austral.edu.TokenType;
import org.austral.edu.TokenTypeV1;
import org.austral.edu.TokenizerV2;

import java.util.List;


public class TokenizerFormatter extends TokenizerV2 {

    String code;
    boolean spaceBeforeDeclaration;
    boolean spaceAfterDeclaration;
    boolean spaceAroundAssignation;
    int newLinesBeforePrint;
    int pointer;
    boolean noSpaces = true;
    boolean singleQuotes;
    public TokenizerFormatter() {
        this.code = "";
        this.pointer = 1;
    }

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (isOperand(token.tokenType)) {
            code += " " + token.content + " ";
            noSpaces = true;
        } else if (token.tokenType.equals(TokenTypeV1.DECLARATION)) {
            code += (spaceBeforeDeclaration? " ":"") + ":" + (spaceAfterDeclaration? " ": "");
            noSpaces = true;
        } else if (token.tokenType.equals(TokenTypeV1.ASSIGNATION)) {
            code += (spaceAroundAssignation? " = ": "=");
            noSpaces = true;
        } else if (token.tokenType.equals(TokenTypeV1.PRINTLN)) {
            for (int i = 0; i < newLinesBeforePrint; i++)
                code += "\n";
            code += token.content;
        } else if (pointer != fromCol && !noSpaces)
            code += " " + formatString(token);
        else {
            code += formatString(token);
            noSpaces = false;
        }
        if (token.tokenType.equals(TokenTypeV1.SEMICOLON)) {
            code += "\n";
            pointer = 1;
            noSpaces = true;
        } else pointer = col;
        return token;
    }

    private boolean isOperand(TokenType token) {
        return List.of(TokenTypeV1.PLUS, TokenTypeV1.MINUS, TokenTypeV1.MULTIPLY, TokenTypeV1.DIVIDE).
                contains(token);
    }

    private String formatString(Token token) {
        if (token.tokenType.equals(TokenTypeV1.STRING)) {
            return (singleQuotes? "'":"\"") + token.content + (singleQuotes? "'":"\"");
        } return token.content;
    }
}
