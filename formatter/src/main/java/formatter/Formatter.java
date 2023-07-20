package formatter;

import exceptions.*;
import org.austral.edu.*;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Formatter extends LexerImpl {

    private final String pathFile;
    private final TokenizerFormatter tokenizer;

    public Formatter(String pathFile, TokenizerFormatter tokenizer) {
        super(tokenizer);
        this.pathFile = pathFile;
        this.tokenizer = tokenizer;
    }

    public void format() throws IOException, UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, ExpressionDetectedException, UnknownTokenException, WrongCaseException {
        setRules();
        lex(new FileInput(pathFile));

        File file = new File(pathFile);
        FileWriter fw = new FileWriter(file);

        fw.write(tokenizer.code);
        fw.flush();
        fw.close();
    }

    private void setRules() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("formatConfig.json")));
            JSONObject jsonObject = new JSONObject(json);
            tokenizer.spaceBeforeDeclaration = jsonObject.getBoolean("spaceBeforeDeclaration");
            tokenizer.spaceAfterDeclaration = jsonObject.getBoolean("spaceAfterDeclaration");
            tokenizer.spaceAroundAssignation = jsonObject.getBoolean("spaceAroundAssignation");
            tokenizer.newLinesBeforePrint = jsonObject.getInt("newLinesBeforePrint");
            tokenizer.singleQuotes = jsonObject.getBoolean("singleQuotes");
            tokenizer.indentSpaces = jsonObject.getInt("indentSpaces");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
