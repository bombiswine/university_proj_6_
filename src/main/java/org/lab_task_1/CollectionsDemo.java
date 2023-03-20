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


    // task 6.11
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
        final Map<Integer,   List<T>>  mapAgeToPeopleList = getMapAgeToListOfPeopleOfThisAge(people);
        final Map<Character, List<T>>  mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter = new HashMap<>();
        char surnameFirstLetter;

        for (int age : mapAgeToPeopleList.keySet()) {
            // iterating through a list of people of current age
            for (T person : mapAgeToPeopleList.get(age)) {
                // for every person of current age, define the mapping
                // character -> people whose surname starts with this character
                if (person != null) {
                    surnameFirstLetter = person.getSurname().charAt(0);
                    if (!mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.containsKey(surnameFirstLetter)) {
                        mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.put(
                            surnameFirstLetter,
                            new LinkedList<>(List.of(person))                       // asa List.of returns immutable list
                        );
                    } else {
                        mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.get(surnameFirstLetter).add(person);
                    }
                }
            }
            // sort every list in current mapping 'char -> people list' by full name
            for (char character : mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.keySet()) {
                mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.get(character).sort(
                    Comparator.comparing(Human::getSurname).
                        thenComparing(Human::getName).
                        thenComparing(Human::getMiddleName)
                );
            }
            // add current intermediate mapping's deep copy to the required mapping
            requiredMap.put(age, new HashMap<>(mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter));
            mapCharacterToPeopleListWhoseSurnameStartsWithGivenCharacter.clear();
        }

        return requiredMap;
    }
}
