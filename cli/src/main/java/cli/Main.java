package cli;

import ast.AbstractSyntaxTree;
import exceptions.UnclosedBracesException;
import exceptions.UnclosedParenthesesException;
import exceptions.UnclosedStringLiteralException;
import org.austral.edu.*;
import org.austral.edu.Results.CLIInput;
import org.austral.edu.Results.CLIResult;
import org.austral.edu.Results.Input;
import org.austral.edu.Results.Result;
import parser.Parser;
import parser.ParserV1;
import parser.ParserV2;
import formatter.*;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Result result = new CLIResult();
    static Input input = new CLIInput();
    static Lexer lexerV1 = new LexerV1();
    static Lexer lexerV2 = new LexerV2();
    static Parser parserV1 = new ParserV1();
    static Parser parserV2 = new ParserV2();
    static InterpreterV1 interpreterV1 = new InterpreterV1(result);
    static InterpreterV2 interpreterV2 = new InterpreterV2(result, input);

    public static void main(String[] args) throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, IOException {

        System.out.println("----- Welcome to PrintScript CLI -----");
        System.out.println();

        String path = getValidFile();

        boolean exit = false;
        while (!exit) {
            String option = optionScreen();

            switch (option) {
                case "1" -> interpretCode(new FileInput(path));
                case "2" -> validateCode(new FileInput(path));
                case "3" -> path = getValidFile();
                case "4" -> exit = true;
                case "5" -> (new Formatter(path, new TokenizerFormatter())).format();
            }
        }
    }

    private static String getValidFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your code snippet file:");
        String path = "";
        while (true) {
            path = scanner.nextLine();
            File file = new File(path);
            if (file.exists()) break;
            System.out.println("File not found.");
        }
        System.out.println();
        return path;
    }

    private static String optionScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would ypu like to do with the snippet?");
        System.out.println("1) Interpret it.");
        System.out.println("2) Validate it.");
        System.out.println("3) Change snippet.");
        System.out.println("4) Exit.");
        System.out.println("5) Formatter.");
        System.out.println("\nChoose:");
        String option = scanner.nextLine();
        while (!List.of("1","2","3","4","5").contains(option)) {
            System.out.println("Please, choose a valid option:");
            option = scanner.nextLine();
        }
        System.out.println();
        return option;
    }

    private static String versionScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which version prefer:");
        System.out.println("1) 1.0");
        System.out.println("2) 1.1");
        System.out.println("\nChoose:");
        String version = scanner.nextLine();
        while (!List.of("1", "2").contains(version)) {
            System.out.println("Please, choose a valid option:");
            version = scanner.nextLine();
        }
        System.out.println();
        return version;
    }

    private static void validateCode(InputProvider code) {
        String version = versionScreen();
        try {
            if (version.equals("1")) {
                List<Token> tokens = lexerV1.lex(code);
                parserV1.parse(tokens);
            } else {
                List<Token> tokens = lexerV2.lex(code);
                parserV2.parse(tokens);
            }
            System.out.println("Validate Successfully.");
        } catch (Throwable e) {
            System.out.println("Validate Error.");
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private static void interpretCode(InputProvider code) {
        String version = versionScreen();
        try {
            if (version.equals("1")) {
                List<Token> tokens = lexerV1.lex(code);
                AbstractSyntaxTree ast = parserV1.parse(tokens);
                interpreterV1.interpret(ast);
            } else {
                List<Token> tokens = lexerV2.lex(code);
                AbstractSyntaxTree ast = parserV2.parse(tokens);
                interpreterV2.interpret(ast);
            }
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
}
