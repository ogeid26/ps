package org.austral.edu.Results;

import java.util.Scanner;

public class CLIInput implements Input {

    @Override
    public String input() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }
}
