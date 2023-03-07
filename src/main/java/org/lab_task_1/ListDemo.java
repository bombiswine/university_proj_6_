package org.lab_task_1;

import org.lab_task_1.human.Human;

import java.util.*;

public class ListDemo {
    public static List<Human> getNamesakesList(List<Human> humans, Human person) {
        List<Human> nameSakes = new LinkedList<>();
        if (humans != null) {
            for (Human human : humans) {
                if (human != null && human.getSurname().equals(person.getSurname())) {
                    nameSakes.add(human);
                }
            }
        }

        return nameSakes;
    }

    public static List<Human> getIndependentListWithoutGivenPerson(final List<Human> people, final Human person) {
        if (person == null) {
            throw new IllegalArgumentException(
                "The null-ref passed as Human argument: person == null"
            );
        }
        if (people == null) {
            throw new IllegalArgumentException(
                "The null-ref passed as List< Human > argument: people == null"
            );
        }

        List<Human> newPeopleList = new LinkedList<>();
        for (Human prsn : people) {
            if (!person.equals(prsn)) {
                newPeopleList.add(prsn == null ? null : new Human(prsn));
            }
        }

        return newPeopleList;
    }

    public static List<Set<Integer>> getListOfSetsHavingEmptyIntersectionWithGivenSet(
        List<Set<Integer>> setList,
        Set<Integer> givenSet
    ) {
        if (givenSet == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to getListOfSetsHavingEmptyIntersectionWithGivenSet as Set<Integer> argument"
            );
        }
        if (setList == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to getListOfSetsHavingEmptyIntersectionWithGivenSet as List<Set<Integer>> argument"
            );
        }

        List<Set<Integer>> ListOfSetsHavingEmptyIntersectionWithGivenSet = new LinkedList<>();
        for (Set<Integer> set : setList) {
            if (!new TreeSet<>(set).removeAll(givenSet)) {
                ListOfSetsHavingEmptyIntersectionWithGivenSet.add(set);
            }
        }

        return ListOfSetsHavingEmptyIntersectionWithGivenSet;
    }

    public static <T extends Human> Set<T> getSetOfOldestPeople(final List<T> givenPeopleList) {
        if (givenPeopleList == null) {
            throw new IllegalArgumentException(
                "The null-ref passed as List<T> people argument into getSetOfOldestPeople"
            );
        }

        Set<T>  oldestPeople = new TreeSet<>();
        List<T> peopleListCopy  = new ArrayList<>(givenPeopleList);
        peopleListCopy.removeAll(Collections.singleton(null));

        if (!peopleListCopy.isEmpty()) {
            peopleListCopy.sort((h1, h2) -> -h1.compareTo(h2));
            final int maxAge = peopleListCopy.get(0).getAge();

            for (T person : peopleListCopy) {
                if (person.getAge() == maxAge) {
                    oldestPeople.add(person);
                } else {
                    break;
                }
            }
        }

        return oldestPeople;
    }
}
