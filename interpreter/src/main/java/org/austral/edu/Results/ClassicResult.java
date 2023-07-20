package org.austral.edu.Results;

import java.util.ArrayList;

public class ClassicResult implements Result{
    ArrayList<String> printElements = new ArrayList<>();
    ArrayList<String> readElements = new ArrayList<>();
    @Override
    public void savePrintElement(String printElement) {
        printElements.add(printElement);
    }

    @Override
    public void saveReaderElement(String readInput) {
        readElements.add(readInput);
    }

    public void print(){
        for (String printElement : printElements) {
            System.out.println(printElement);
        }
    }

    public void showInputs(){
        for (String readInput : readElements) {
            System.out.println(readInput);
        }
    }

    public ArrayList<String> getPrintElements() {
        return printElements;
    }

    public ArrayList<String> getReadElements() {
        return readElements;
    }
}
