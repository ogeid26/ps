package org.austral.edu.Results;

public class ClassicInput implements Input {

    public String value;

    public ClassicInput(String value) {
        this.value = value;
    }

    @Override
    public String input() {
        return this.value;
    }
}
