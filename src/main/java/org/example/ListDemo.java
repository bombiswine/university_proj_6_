package org.example;

import org.example.human.Human;

import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static List< Human > getNamesakesList(List< Human > humans, Human person) {
        List< Human > nameSakes = new LinkedList<>();
        if (humans != null) {
            for (Human human : humans) {
                if (human != null && human.getSurname().equals(person.getSurname())) {
                    nameSakes.add(human);
                }
            }
        }

        return nameSakes;
    }

    public static List< Human > getIndependentListWithoutGivenPerson(final List< Human > people, final Human person) {
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

        List < Human > newPeopleList = new LinkedList<>();
        for (Human prsn : people) {
            if (!person.equals(prsn)) {
                newPeopleList.add(prsn == null ? null : new Human(prsn));
            }
        }

        return newPeopleList;
    }
}
