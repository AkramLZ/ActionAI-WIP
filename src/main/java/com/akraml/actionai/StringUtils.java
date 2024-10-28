package com.akraml.actionai;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static List<String> splitCommand(String input) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : input.toCharArray()) {
            if (c == '\"') {
                inQuotes = !inQuotes; // Toggle the inQuotes flag
            } else if (c == ' ' && !inQuotes) {
                if (!current.isEmpty()) {
                    result.add(current.toString());
                    current.setLength(0); // Reset the current builder
                }
            } else {
                current.append(c);
            }
        }

        // Add the last segment if it exists
        if (!current.isEmpty()) {
            result.add(current.toString());
        }

        return result;
    }

}
