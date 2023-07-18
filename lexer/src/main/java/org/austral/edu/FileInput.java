package org.austral.edu;

import java.io.*;
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
            String code = bufferedReader.lines().collect(Collectors.joining());
            bufferedReader.close();
            return code;
        } catch (IOException e) {
            return "";
        }
    }
}
