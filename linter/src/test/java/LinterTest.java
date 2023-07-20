import exceptions.ExpressionDetectedException;
import exceptions.UnclosedBracesException;
import exceptions.UnclosedParenthesesException;
import exceptions.UnclosedStringLiteralException;
import linter.Linter;
import linter.TokenizerAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class LinterTest {

    @Test
    public void test001_Println_Text() throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, IOException {
        Linter linter = new Linter("test.txt",new TokenizerAnalyzer());
        assertThrows(ExpressionDetectedException.class, linter::lint);
    }

    @Test
    public void test002_Println_Variable() throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, IOException {
        Linter linter = new Linter("test1.txt",new TokenizerAnalyzer());
        assertDoesNotThrow(linter::lint);
    }

    @Test
    public void test001_ReadInput_Text() throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, IOException {
        Linter linter = new Linter("test2.txt",new TokenizerAnalyzer());
        assertThrows(ExpressionDetectedException.class, linter::lint);
    }

    @Test
    public void test002_ReadInput_Variable() throws UnclosedBracesException, UnclosedParenthesesException, UnclosedStringLiteralException, IOException {
        Linter linter = new Linter("test3.txt",new TokenizerAnalyzer());
        assertDoesNotThrow(linter::lint);
    }
}
