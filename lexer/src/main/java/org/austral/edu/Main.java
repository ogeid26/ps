package org.austral.edu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new LexerImpl();

        String x = """
                let x:string = "Hello";
                let a:number = 5;
                let b: number = 7;
               """;
        InputStream inputStream = new FileInputStream("test.txt");
        for (int i = 0; i < 100; i++) {
            System.out.print((char) inputStream.read());
        }

    }
}
