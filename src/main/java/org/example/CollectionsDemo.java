package org.example;

import java.util.List;

public class CollectionsDemo {
    public static int countStringStartsWith(List<String> strings, final char ch) {
        if (strings == null || strings.isEmpty()) {
            return 0;
        }
        int counter = 0;
        for (var string : strings) {
            if (string != null && string.startsWith(Character.toString(ch))) {
                counter++;
            }
        }

        return counter;
    }


}
