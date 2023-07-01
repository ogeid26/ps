package org.austral.edu.Helpers;

public class TextHelper {
    public Object parseString(String input) {
        try {
            double doubleValue = Double.parseDouble(input);
            if (doubleValue == (int) doubleValue) {
                return (int) doubleValue;
            } else {
                return doubleValue;
            }
        } catch (NumberFormatException e) {
            return input;
        }
    }
}
