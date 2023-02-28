package org.lab_task_1;

import org.lab_task_1.human.Human;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsDemo {
    public static int countStringStartsWith(
        final List<String> strings,
        final char ch
    ) {
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

    public static <T extends Human> Set<T> getPeopleWithIdentifiersFromGivenSet(
        final Map<Integer, T> map,
        final Set<Integer>    keys
    ) {
        if (keys == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getPeopleWithIdentifiersFromGivenSet:\n" +
                " Set<Integer> keys is null"
            );
        }
        if (map == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getPeopleWithIdentifiersFromGivenSet:\n " +
                "Map<Integer, T> map is null"
            );
        }
        if (map.containsValue(null)) {
            throw new IllegalArgumentException(
                "The null-ref passed to getPeopleWithIdentifiersFromGivenSet:\n" +
                "Map<Integer, T> map contains null as its value"
            );
        }

        Set<T> peopleWithIdentifiersFromGivenSet = new TreeSet<>();
        for (int key : map.keySet()) {
            if (keys.contains(key)) {
                peopleWithIdentifiersFromGivenSet.add(map.get(key));
            }
        }

        return peopleWithIdentifiersFromGivenSet;
    }

    public static <T extends Human> Set<Integer> getKeysOfAdultsInGivenMap(
        final Map<Integer, T> map,
        final Set<Integer>    keys
    ) {
        if (keys == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getKeysOfAdultsInGivenMap:\n" +
                " Set<Integer> keys is null"
            );
        }
        if (map == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getKeysOfAdultsInGivenMap:\n " +
                "Map<Integer, T> map is null"
            );
        }
        if (map.containsValue(null)) {
            throw new IllegalArgumentException(
                "The null-ref passed to getKeysOfAdultsInGivenMap:\n" +
                "Map<Integer, T> map contains null as its value"
            );
        }

        Set<Integer> adultsWithKeysFromGivenSet = new TreeSet<>();
        for (int key : map.keySet()) {
            if (keys.contains(key) && map.get(key).isAdult()) {
                adultsWithKeysFromGivenSet.add(key);
            }
        }

        return adultsWithKeysFromGivenSet;
    }
}
