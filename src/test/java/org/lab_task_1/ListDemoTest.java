package org.lab_task_1;

import org.lab_task_1.human.Human;
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
        List<Human> humans,
        Human person,
        List<Human> expectedList
    ) {
        List<Human> actualList = getNamesakesList(humans, person);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getNamesakesList_Data() {
        List<Human> humanList = List.of(personLucyEarl, personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        List<Human> expectedList1 = List.of(personLucyVirth, personJulieVirth);
        List<Human> expectedList2 = List.of(personAlexandreMerson);
        List<Human> emptyList = new ArrayList<>(0);

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
        List<Human> actualList = getIndependentListWithoutGivenPerson(people, person);
        assertEquals(actualList, expectedList);
        assertNotSame(
            people.get(people.indexOf(personLucyBrown)), actualList.get(actualList.indexOf(personLucyBrown)));
    }

    @DataProvider
    public static Object[][] getIndependentListWithoutGivenPerson_PositiveCase_Data() {
        List<Human> initialList = List.of(personLucyEarl, personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        List<Human> initialListWithNull = Arrays.asList(personLucyEarl, null, personAlexandreMerson, personJulieVirth, personLucyBrown);
        List<Human> expectedList1 = List.of(personLucyEarl, personLucyVirth, personJulieVirth, personLucyBrown);
        List<Human> expectedList1WithNull = Arrays.asList(personLucyEarl, null, personJulieVirth, personLucyBrown);
        List<Human> expectedList2 = List.of(personLucyVirth, personAlexandreMerson, personJulieVirth, personLucyBrown);
        List<Human> expectedList2WithNull = Arrays.asList(null, personAlexandreMerson, personJulieVirth, personLucyBrown);

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
        List<Set<Integer>> setList,
        Set<Integer> givenSet,
        List<Set<Integer>> expectedList
    ) {
        List<Set<Integer>> actualList = getListOfSetsHavingEmptyIntersectionWithGivenSet(setList, givenSet);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getListOfSetsHavingEmptyIntersectionWithGivenSet_PositiveCase_Data() {
        Set<Integer> emptySet = new TreeSet<>();
        Set<Integer> setA = new TreeSet<>(List.of(1, 2, 3));
        Set<Integer> setB = new TreeSet<>(List.of(4, 5, 6));
        Set<Integer> setC = new TreeSet<>(List.of(-1, 10, 7));

        Set<Integer> setUnionBAndA = new TreeSet<>();
        setUnionBAndA.addAll(setA);
        setUnionBAndA.addAll(setB);

        Set<Integer> setUnionCAndA = new TreeSet<>();
        setUnionCAndA.addAll(setA);
        setUnionCAndA.addAll(setC);

        List<Set<Integer>> emptyList = new ArrayList<>(0);
        List<Set<Integer>> listOfEmptySet = List.of(new TreeSet<>());
        List<Set<Integer>> listOfGivenSetCopies = List.of(setA, setA, setA);
        List<Set<Integer>> listOfSetsHavingEmptyIntersectionWithSetA = List.of(setB, setC, emptySet);
        List<Set<Integer>> listContainsSetsThatIntersectsWithSetA = List.of(setB, setC, setUnionBAndA, setUnionCAndA, emptySet);

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
        List<Human> peopleListWithOneOldestPerson = List.of(
            personLucyEarl, personAlexandreMerson, personCyrillVirth, personLucyBrown
        );
        List<Human> peopleListWithOneOldestPersonAndNulls = Arrays.asList(
            personLucyEarl, null, personAlexandreMerson, personCyrillVirth, null, personLucyBrown
        );
        Set<Human> oldestPerson  = new TreeSet<>(List.of(personCyrillVirth));

        List<Student> studentsListWithOneOldestPerson = List.of(
            studentLucyEarl, studentAlexandreMerson, studentCyrillVirth, studentLucyBrown
        );
        List<Student> studentsListWithOneOldestPersonAndNulls = Arrays.asList(
            studentLucyEarl, null, studentAlexandreMerson, studentCyrillVirth, null, studentLucyBrown
        );
        Set<Student> oldestStudent = new TreeSet<>(List.of(studentCyrillVirth));

        List<Human> peopleListWithTwoOldestPeople = List.of(
            personLucyEarl, personOlgaMerson, personAlexandreMerson, personCyrillVirth, personLucyBrown
        );
        Set<Human>  twoOldestPeople = new TreeSet<>(List.of(personOlgaMerson, personCyrillVirth));

        List<Student> studentsListWithTwoOldestStudents = List.of(
            studentLucyEarl, studentOlgaMerson, studentAlexandreMerson, studentCyrillVirth, studentLucyBrown
        );
        Set<Student>  twoOldestStudents = new TreeSet<>(List.of(studentOlgaMerson,studentCyrillVirth));

        List<Human> peopleListWithSameAgePeople = List.of(
            personLucyBrown, personLucyVirth, personLucyGreen
        );
        List<Student> studentsListWithSameAgeStudents = List.of(
            studentLucyBrown,  studentLucyVirth, studentLucyGreen
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
