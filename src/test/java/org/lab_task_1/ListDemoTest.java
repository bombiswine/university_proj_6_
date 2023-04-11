package org.lab_task_1;

import org.human.Human;
import org.lab_task_1.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.lab_task_1.DataForTesting.*;
import static org.lab_task_1.ListDemo.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

public class ListDemoTest {
    @Test(dataProvider = "getNamesakesList_Data")
    public static void getNamesakesList_Test(
        final List<Human> humans,
        final Human person,
        final List<Human> expectedList
    ) {
        final List<Human> actualList = getNamesakesList(humans, person);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getNamesakesList_Data() {
        final List<Human> humanList = List.of(personLucyEarl, personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        final List<Human> expectedList1 = List.of(personLucyVirth, personJulieVirth);
        final List<Human> expectedList2 = List.of(personAlexandreMerson);
        final List<Human> emptyList = new ArrayList<>(0);

        return new Object[][] {
            { humanList, personCyrillVirth, expectedList1 },
            { humanList, personOlgaMerson, expectedList2 },
            { humanList, personLucyGreen, emptyList },
            { null, personCyrillVirth, emptyList },
            { null, personOlgaMerson, emptyList },
            { null, personLucyGreen, emptyList },
            { emptyList, personCyrillVirth, emptyList },
            { emptyList, personOlgaMerson, emptyList },
            { emptyList, personLucyGreen, emptyList },
        };
    }

    @Test(dataProvider = "getIndependentListWithoutGivenPerson_PositiveCase_Data")
    public static void getIndependentListWithoutGivenPerson_PositiveCase_Test(
        final List<Human> people,
        final Human person,
        final List<Human> expectedList
    ) {
        final List<Human> actualList = getIndependentListWithoutGivenPerson(people, person);
        assertEquals(actualList, expectedList);
        assertNotSame(
            people.get(people.indexOf(personLucyBrown)), actualList.get(actualList.indexOf(personLucyBrown))
        );
    }

    @DataProvider
    public static Object[][] getIndependentListWithoutGivenPerson_PositiveCase_Data() {
        final List<Human> initialList = List.of(personLucyEarl, personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        final List<Human> initialListWithNull = Arrays.asList(personLucyEarl, null, personAlexandreMerson, personJulieVirth, personLucyBrown);
        final List<Human> expectedList1 = List.of(personLucyEarl, personLucyVirth, personJulieVirth, personLucyBrown);
        final List<Human> expectedList1WithNull = Arrays.asList(personLucyEarl, null, personJulieVirth, personLucyBrown);
        final List<Human> expectedList2 = List.of(personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        final List<Human> expectedList2WithNull = Arrays.asList(null, personAlexandreMerson, personJulieVirth, personLucyBrown);

        return new Object[][] {
            { initialList, personAlexandreMerson, expectedList1 },
            { initialListWithNull, personAlexandreMerson, expectedList1WithNull },
            { initialList, personLucyEarl, expectedList2 },
            { initialListWithNull, personLucyEarl, expectedList2WithNull },
            { initialList, personOlgaMerson, initialList },
            { initialListWithNull, personOlgaMerson, initialListWithNull },
        };
    }

    @Test(dataProvider = "getListOfSetsHavingEmptyIntersectionWithGivenSet_PositiveCase_Data")
    public static void getListOfSetsHavingEmptyIntersectionWithGivenSet_PositiveCase_Test(
        final List<Set<Integer>> setList,
        final Set<Integer> givenSet,
        final List<Set<Integer>> expectedList
    ) {
        List<Set<Integer>> actualList = getListOfSetsHavingEmptyIntersectionWithGivenSet(setList, givenSet);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getListOfSetsHavingEmptyIntersectionWithGivenSet_PositiveCase_Data() {
        final Set<Integer> emptySet = new TreeSet<>();
        final Set<Integer> setA = new TreeSet<>(List.of(1, 2, 3));
        final Set<Integer> setB = new TreeSet<>(List.of(4, 5, 6));
        final Set<Integer> setC = new TreeSet<>(List.of(-1, 10, 7));

        final Set<Integer> setUnionBAndA = new TreeSet<>();
        setUnionBAndA.addAll(setA);
        setUnionBAndA.addAll(setB);

        final Set<Integer> setUnionCAndA = new TreeSet<>();
        setUnionCAndA.addAll(setA);
        setUnionCAndA.addAll(setC);

        final List<Set<Integer>> emptyList = new ArrayList<>(0);
        final List<Set<Integer>> listOfEmptySet = List.of(new TreeSet<>());
        final List<Set<Integer>> listOfGivenSetCopies = List.of(setA, setA, setA);
        final List<Set<Integer>> listOfSetsHavingEmptyIntersectionWithSetA = List.of(setB, setC, emptySet);
        final List<Set<Integer>> listContainsSetsThatIntersectsWithSetA = List.of(setB, setC, setUnionBAndA, setUnionCAndA, emptySet);

        return new Object[][] {
            { emptyList, setA, emptyList },
            { listOfEmptySet, setA, listOfEmptySet },
            { listOfGivenSetCopies, setA, emptyList },
            { listOfGivenSetCopies, setA, emptyList },
            { listOfSetsHavingEmptyIntersectionWithSetA, setA, listOfSetsHavingEmptyIntersectionWithSetA },
            { listContainsSetsThatIntersectsWithSetA, setA, listOfSetsHavingEmptyIntersectionWithSetA },

            { emptyList, emptySet, emptyList },
            { listOfEmptySet, emptySet, listOfEmptySet },
            { listContainsSetsThatIntersectsWithSetA, emptySet, listContainsSetsThatIntersectsWithSetA },
        };
    }

    @Test(dataProvider = "getSetOfOldestPeople_PositiveCase_Data")
    public static <T extends Human> void getSetOfOldestPeople_PositiveCase_Test(
        final List<T> people,
        final Set<T> expectedOldestPeople
    ) {
        final Set<T> actualOldestPeople = getSetOfOldestPeople(people);
        assertEquals(actualOldestPeople, expectedOldestPeople);
    }

    @DataProvider
    public static Object[][] getSetOfOldestPeople_PositiveCase_Data() {
        final List<Human> peopleListWithOneOldestPerson = List.of(
            personLucyEarl, personAlexandreMerson, personCyrillVirth, personLucyBrown
        );
        final List<Human> peopleListWithOneOldestPersonAndNulls = Arrays.asList(
            personLucyEarl, null, personAlexandreMerson, personCyrillVirth, null, personLucyBrown
        );
        final Set<Human> oldestPerson = Set.of(personCyrillVirth);

        final List<Student> studentsListWithOneOldestPerson = List.of(
            studentLucyEarl, studentAlexandreMerson, studentCyrillVirth, studentLucyBrown
        );
        final List<Student> studentsListWithOneOldestPersonAndNulls = Arrays.asList(
            studentLucyEarl, null, studentAlexandreMerson, studentCyrillVirth, null, studentLucyBrown
        );
        final Set<Student> oldestStudent = Set.of(studentCyrillVirth);

        final List<Human> peopleListWithTwoOldestPeople = List.of(
            personLucyEarl, personOlgaMerson, personAlexandreMerson, personCyrillVirth, personLucyBrown
        );
        final Set<Human> twoOldestPeople = new TreeSet<>(List.of(personOlgaMerson, personCyrillVirth));

        final List<Student> studentsListWithTwoOldestStudents = List.of(
            studentLucyEarl, studentOlgaMerson, studentAlexandreMerson, studentCyrillVirth, studentLucyBrown
        );
        final Set<Student> twoOldestStudents = new TreeSet<>(List.of(studentOlgaMerson, studentCyrillVirth));

        final List<Human> peopleListWithSameAgePeople = List.of(
            personLucyBrown, personLucyVirth, personLucyGreen
        );
        final List<Student> studentsListWithSameAgeStudents = List.of(
            studentLucyBrown, studentLucyVirth, studentLucyGreen
        );

        return new Object[][] {
            { peopleListWithOneOldestPerson, oldestPerson },
            { peopleListWithTwoOldestPeople, twoOldestPeople },
            { peopleListWithSameAgePeople, new TreeSet<>(peopleListWithSameAgePeople) },
            { new ArrayList<Human>(0), new TreeSet<Human>() },
            { peopleListWithOneOldestPersonAndNulls, oldestPerson },

            { Arrays.asList(null, null, null), new TreeSet<Human>() },

            { studentsListWithOneOldestPerson, oldestStudent },
            { studentsListWithTwoOldestStudents, twoOldestStudents },
            { studentsListWithSameAgeStudents, new TreeSet<>(peopleListWithSameAgePeople) },
            { new ArrayList<Student>(0), new TreeSet<Student>() },
            { studentsListWithOneOldestPersonAndNulls, oldestStudent },
        };
    }
}
