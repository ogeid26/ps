package formatter;

import org.austral.edu.*;

import java.util.List;


public class TokenizerFormatter extends TokenizerV2 {

    String code;
    boolean spaceBeforeDeclaration;
    boolean spaceAfterDeclaration;
    boolean spaceAroundAssignation;
    int newLinesBeforePrint;
    int pointer;
    boolean noSpaces;
    boolean singleQuotes;
    int indentSpaces;
    int blockCode;
    boolean newLine;

    public TokenizerFormatter() {
        this.code = "";
        this.pointer = 1;
        this.noSpaces = true;
        this.blockCode = 0;
    }

    @Override
    public Token tokenize(String currentString, int from, int fromCol, int col, int row) {
        Token token = super.tokenize(currentString, from, fromCol, col, row);
        if (blockCode > 0 && newLine && !token.tokenType.equals(TokenTypeV1.PRINTLN)) {
            if (token.tokenType.equals(TokenTypeV2.R_BRACES)) {
                for (int i = 0; i < (blockCode-1) * indentSpaces; i++) {
                    code += " ";
                }
            } else {
                for (int i = 0; i < blockCode * indentSpaces; i++) {
                    code += " ";
                }
            }
        }
        if (token.tokenType.equals(TokenTypeV2.L_BRACES)) {
            blockCode += 1;
            newLine = true;
            code += " {\n";
        } else if (token.tokenType.equals(TokenTypeV2.R_BRACES)) {
            blockCode -= 1;
            newLine = true;
            code += "}\n";
        } else if (isOperand(token.tokenType)) {
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
            if (blockCode > 0) {
                for (int i = 0; i < blockCode * indentSpaces; i++) {
                    code += " ";
                }
            }
            code += token.content;
            newLine = false;
        } else if (pointer != fromCol && !noSpaces && !newLine)
            code += " " + formatString(token);
        else {
            code += formatString(token);
            noSpaces = false;
            newLine = false;
        }
        if (token.tokenType.equals(TokenTypeV1.SEMICOLON)) {
            code += "\n";
            pointer = 1;
            noSpaces = true;
            newLine = true;
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
