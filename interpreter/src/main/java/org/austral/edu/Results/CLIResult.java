package org.austral.edu.Results;

public class CLIResult implements Result {
    @Override
    public void savePrintElement(String printElement) {
        System.out.println(printElement);
    }

    @Override
    public void saveReaderElement(String readInput) {
        System.out.println(readInput);
    }
}
