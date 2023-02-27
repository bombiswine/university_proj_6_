package org.lab_task_1;

import java.util.List;

public class CollectionsDemo {
    public static int countStringStartsWith(final List<String> strings, final char ch) {
        if (strings == null || strings.isEmpty()) {
            return 0;
        }
        int counter = 0;
        for (String string : strings) {
            if (string != null && string.startsWith(Character.toString(ch))) {
                counter++;
            }
        }

        return counter;
    }
}
