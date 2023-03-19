package org.lab_task_1;

import org.lab_task_1.human.Human;

import java.util.*;

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
        final Set<Integer> keys
    ) {
        if (keys == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getPeopleWithIdentifiersFromGivenSet:" + System.lineSeparator() +
                    " Set<Integer> keys is null"
            );
        }
        if (map == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getPeopleWithIdentifiersFromGivenSet:\n " +
                    "Map<Integer, T> map is null"
            );
        }

        Set<T> peopleWithIdentifiersFromGivenSet = new HashSet<>();
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            if (keys.contains(entry.getKey()) && entry.getValue() != null) {
                peopleWithIdentifiersFromGivenSet.add(entry.getValue());
            }
        }

        return peopleWithIdentifiersFromGivenSet;
    }

    public static <T extends Human> Set<Integer> getKeysOfAdultsInGivenMap(
        final Map<Integer, T> map,
        final Set<Integer> keys
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

        final Set<Integer> adultsWithKeysFromGivenSet = new TreeSet<>();
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            if (keys.contains(entry.getKey()) && entry.getValue() != null && entry.getValue().isAdult()) {
                adultsWithKeysFromGivenSet.add(entry.getKey());
            }
        }

        return adultsWithKeysFromGivenSet;
    }

    // task 6.9
    public static <T extends Human> Map<Integer, Integer> getMapKeyToAge(
        final Map<Integer, T> map
    ) {
        if (map == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getMapKeyToAge: " + System.lineSeparator() +
                    "Map<Integer, T> map is null"
            );
        }

        final Map<Integer, Integer> keyToAge = new HashMap<>();
        for (Map.Entry<Integer, T> entry : map.entrySet()) {
            if (entry.getValue() != null) {
                keyToAge.put(entry.getKey(), entry.getValue().getAge());
            }
        }

        return keyToAge;
    }

    // task 6.10
    public static <T extends Human> Map<Integer, List<T>> getMapAgeToListOfPeopleOfThisAge(
        final Set<T> peopleSet
    ) {
        if (peopleSet == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getMapAgeToPeopleOfThisAge:" + System.lineSeparator() +
                "Set<T> peopleSet is null"
            );
        }
        final Map<Integer, List<T>> ageToPeopleMap = new HashMap<>();
        for (T person : peopleSet) {
            if (person != null) {
                if (!ageToPeopleMap.containsKey(person.getAge())) {
                    ageToPeopleMap.put(person.getAge(), new LinkedList<>(List.of(person)));
                } else {
                    ageToPeopleMap.get(person.getAge()).add(person);
                }
            }
        }

        return ageToPeopleMap;
    }

    // task 6.6
    public static <T extends Human> List<T> getSortedByFullNameList(
        final Set<T> people
    ) {
        if (people == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getSortedByFullNameList:" + System.lineSeparator() +
                "Set<T> people is null"
            );
        }
        List<T> sortedList = new LinkedList<>(people);
        sortedList.sort(
            Comparator.comparing(Human::getSurname).
            thenComparing(Human::getName).
            thenComparing(Human::getMiddleName)
        );

        return sortedList;
    }

    public static <T extends Human> Map<Integer, Map<Character, List<T>>> getMappingAgeToMappingLetterToPeopleList(
        final Set<T> people
    ) {
        if (people == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getMappingAgeToMappingLetterToPeopleList:" + System.lineSeparator() +
                "Set<T> people is null"
            );
        }

        final Map<Integer, Map<Character, List<T>>> requiredMap = new HashMap<>();
        final Map<Integer,   List<T>>   mapAgeToPeople = getMapAgeToListOfPeopleOfThisAge(people);
        final Map<Character, List<T>>   intermediateMapCharacterToPeople = new HashMap<>();
        char  currentSurnameFirstLetter;

        for (int age : mapAgeToPeople.keySet()) {
            for (T person : mapAgeToPeople.get(age)){
                if (person != null) {
                    currentSurnameFirstLetter = person.getSurname().charAt(0);
                    if (!intermediateMapCharacterToPeople.containsKey(currentSurnameFirstLetter)) {
                        intermediateMapCharacterToPeople.put(
                            currentSurnameFirstLetter,
                            new LinkedList<>(List.of(person))
                        );
                    } else {
                        intermediateMapCharacterToPeople.get(currentSurnameFirstLetter).add(person);
                    }
                }
            }
            requiredMap.put(age, intermediateMapCharacterToPeople);
            intermediateMapCharacterToPeople.clear();
        }

        return requiredMap;
    }
}
