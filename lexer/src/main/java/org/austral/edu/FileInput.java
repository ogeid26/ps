package org.austral.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Collectors;

public class FileInput implements InputProvider {

    private final String path;

    public FileInput(String path) {
        this.path = path;
    }

    @Override
    public String getContent() {
        try {
            File file = new File(this.path);
            FileReader stream = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(stream);
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}
