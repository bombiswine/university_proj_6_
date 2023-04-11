package org.lab_task_1;

import org.human.Human;
import org.lab_task_1.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.lab_task_1.CollectionsDemo.*;
import static org.lab_task_1.DataForTesting.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CollectionsDemoTest {
    @Test(dataProvider = "countStringStartsWith_Data")
    public static void countStringStartsWith_Test(
        List<String> strings,
        char ch,
        int expectedAmount
    ) {
        int actualAmount = countStringStartsWith(strings, ch);
        assertEquals(actualAmount, expectedAmount);
    }

    @DataProvider
    public static Object[][] countStringStartsWith_Data() {
        String[] testData1 = new String[] { "Array", "Aura", "Australia" };
        String[] testData2 = new String[] { "array", "aura", "australia" };
        String[] testData3 = new String[] { "array", "laura", "Ostralia" };
        String[] testData4 = new String[] { "Array", "aura", "Australia" };

        return new Object[][] {
            { Arrays.asList(testData1), 'A', 3 },
            { Arrays.asList(testData2), 'A', 0 },
            { Arrays.asList(testData3), 'O', 1 },
            { Arrays.asList(testData4), 'A', 2 },
            { null, 'F', 0 },
            { new ArrayList<String>(0), 'F', 0 },
        };
    }

    // task 6.6
    @Test(dataProvider = "getPeopleWithIdentifiersFromGivenSet_PositiveCase_Data")
    public static <T extends Human> void getPeopleWithIdentifiersFromGivenSet_PositiveCase_Test(
        final Map<Integer, T> map,
        final Set<Integer>    keys,
        final Set<T>          expectedPeopleSet
    ) {
        final Set<T> actualPeopleSet = getPeopleWithIdentifiersFromGivenSet(map, keys);
        assertEquals(actualPeopleSet, expectedPeopleSet);
    }

    @DataProvider
    public static Object[][] getPeopleWithIdentifiersFromGivenSet_PositiveCase_Data() {
        final Set<Integer> allKeysSet          = Set.of(0, 1, 2, 3, 4, 5, 6, 7);
        final Set<Integer> evenKeysSet         = Set.of(0, 2, 4, 6);
        final Set<Integer> randomKeysSet       = Set.of(0, 12, 1, 7, -5, 32, -13);
        final Set<Integer> notPresentedKeysSet = Set.of(12, -21, 17, -5, 32, -13);

        final Set<Human>  peopleWithEvenKeys = Set.of(
            personLucyEarl,
            personCyrillVirth,
            personOlgaMerson,
            personLucyVirth
        );
        final Set<Human>  peopleWithKeysFromRandomKeysSet = Set.of(
            personLucyEarl,
            personAlexandreMerson,
            personLucyGreen
        );
        final Set<Human> nonNullPeopleSet = Set.of(
            personAlexandreMerson,
            personLucyBrown,
            personLucyGreen,
            personOlgaMerson
        );

        final Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(0, personLucyBrown);
        mapWithNulls.put(1, personAlexandreMerson);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personLucyBrown);
        mapWithNulls.put(4, personLucyGreen);
        mapWithNulls.put(5, null);
        mapWithNulls.put(6, personOlgaMerson);
        mapWithNulls.put(7, null);

        final Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personAlexandreMerson,
            2, personCyrillVirth,
            3, personLucyBrown,
            4, personOlgaMerson,
            5, personJulieVirth,
            6, personLucyVirth,
            7, personLucyGreen
        );

        return new Object[][] {
            { mapWithoutNull, allKeysSet, new HashSet<>(mapWithoutNull.values()) },
            { mapWithoutNull, evenKeysSet, peopleWithEvenKeys },
            { mapWithoutNull, randomKeysSet, peopleWithKeysFromRandomKeysSet },
            { mapWithoutNull, notPresentedKeysSet, new HashSet<>() },
            { mapWithoutNull, new HashSet<>(), new HashSet<>() },
            { new HashMap<>(), allKeysSet, new HashSet<>() },
            { new HashMap<>(), evenKeysSet, new HashSet<>() },
            { new HashMap<>(), randomKeysSet, new HashSet<>() },
            { new HashMap<>(), new HashSet<>(), new HashSet<>() },
            { mapWithNulls, allKeysSet, nonNullPeopleSet },
        };
    }

    @Test(
        dataProvider = "getPeopleWithIdentifiersFromGivenSet_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static <T extends Human> void getPeopleWithIdentifiersFromGivenSet_ThrowsIllegalArgumentException_Test(
        final Map<Integer, T> map,
        final Set<Integer>    keys
    ) {
        getPeopleWithIdentifiersFromGivenSet(map, keys);
    }

    @DataProvider
    public static Object[][] getPeopleWithIdentifiersFromGivenSet_ThrowsIllegalArgumentException_Data() {
        Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personAlexandreMerson,
            2, personCyrillVirth,
            3, personLucyBrown,
            4, personOlgaMerson,
            5, personJulieVirth,
            6, personLucyVirth,
            7, personLucyGreen
        );

        return new Object[][] {
            { null, mapWithoutNull.keySet() },
            { mapWithoutNull, null },
        };
    }

    // task 6.8
    @Test(dataProvider = "getKeysOfAdultsInGivenMap_PositiveCase_Data")
    public static <T extends Human> void getKeysOfAdultsInGivenMap_PositiveCase_Test(
        final Map<Integer, T> map,
        final Set<Integer>    keys,
        final Set<Integer>    expectedKeysSet
    ) {
        final Set<Integer> actualPeopleSet = getKeysOfAdultsInGivenMap(map, keys);
        assertEquals(actualPeopleSet, expectedKeysSet);
    }

    @DataProvider
    public static Object[][] getKeysOfAdultsInGivenMap_PositiveCase_Data() {
        final Set<Integer> allKeysSet          = Set.of(0, 1, 2, 3);
        final Set<Integer> randomKeysSet       = Set.of(0, 12, 1, 7, -5, 32, -13);
        final Set<Integer> notPresentedKeysSet = Set.of(12, -21, 17, -5, 32, -13);

        final Map<Integer, Human> mapWithAdultsAndChildren = Map.of(
            0, personLucyEarl,
            1, personNicolasBeau,
            2, personAlexandreMerson,
            3, personAnnetBeaumarchais
        );
        final Map<Integer, Human> mapWithAdultsOnly = Map.of(
            0, personLucyEarl,
            1, personAlexandreMerson,
            2, personCyrillVirth,
            3, personLucyGreen
        );
        final Map<Integer, Human> mapWithChildrenOnly = Map.of(
            0, personNicolasBeau,
            1, personAnnetBeaumarchais,
            2, personNicolasBeau,
            3, personAnnetBeaumarchais
        );

        final Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(0, personLucyEarl);
        mapWithNulls.put(1, personNicolasBeau);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personCyrillVirth);
        mapWithNulls.put(4, personLucyEarl);
        mapWithNulls.put(5, personAlexandreMerson);
        mapWithNulls.put(6, null);
        mapWithNulls.put(7, personAnnetBeaumarchais);

        return new Object[][] {
            { mapWithAdultsAndChildren, allKeysSet, Set.of(0, 2) },
            { mapWithAdultsAndChildren, randomKeysSet, Set.of(0) },
            { mapWithAdultsAndChildren, notPresentedKeysSet, Set.of() },
            { mapWithAdultsAndChildren, Set.of(), Set.of() },

            { mapWithAdultsOnly, allKeysSet, allKeysSet },
            { mapWithAdultsOnly, randomKeysSet, Set.of(0, 1) },
            { mapWithAdultsOnly, notPresentedKeysSet, Set.of() },
            { mapWithAdultsOnly, Set.of(), Set.of() },

            { mapWithChildrenOnly, allKeysSet, Set.of() },
            { mapWithChildrenOnly, randomKeysSet, Set.of() },
            { mapWithChildrenOnly, notPresentedKeysSet, Set.of() },
            { mapWithChildrenOnly, Set.of(), Set.of() },

            { Map.of(), allKeysSet, Set.of() },
            { Map.of(), Set.of(), Set.of() },
            { mapWithNulls, allKeysSet, Set.of(0, 3) },
        };
    }

    @Test(
        dataProvider = "getKeysOfAdultsInGivenMap_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static <T extends Human> void getKeysOfAdultsInGivenMap_ThrowsIllegalArgumentException_Test(
        final Map<Integer, T> map,
        final Set<Integer>    keys
    ) {
        getKeysOfAdultsInGivenMap(map, keys);
    }

    @DataProvider
    public static Object[][] getKeysOfAdultsInGivenMap_ThrowsIllegalArgumentException_Data() {
        final Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personNicolasBeau,
            2, personAlexandreMerson,
            3, personAnnetBeaumarchais
        );

        return new Object[][] {
            { null, mapWithoutNull.keySet() },
            { mapWithoutNull, null },
        };
    }

    @Test(dataProvider = "getMapKeyToAge_PositiveCase_Data")
    public static <T extends Human> void getMapKeyToAge_PositiveCase_Test(
        final Map<Integer, T> givenMap,
        final Map<Integer, Integer> expectedMap
    ) {
        final Map<Integer, Integer> actualMap = getMapKeyToAge(givenMap);
        assertEquals(actualMap, expectedMap);
    }

    @DataProvider
    public static Object[][] getMapKeyToAge_PositiveCase_Data() {
        final Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personNicolasBeau,
            2, personAlexandreMerson,
            3, personAnnetBeaumarchais
        );
        final Map<Integer, Integer> expectedMap1 = Map.of(
            0, personLucyEarl.getAge(),
            1, personNicolasBeau.getAge(),
            2, personAlexandreMerson.getAge(),
            3, personAnnetBeaumarchais.getAge()
        );

        final Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(15, personOlgaMerson);
        mapWithNulls.put(0, personLucyEarl);
        mapWithNulls.put(1, personNicolasBeau);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personCyrillVirth);
        mapWithNulls.put(4, personLucyEarl);
        mapWithNulls.put(5, personAlexandreMerson);
        mapWithNulls.put(6, null);
        mapWithNulls.put(7, personAnnetBeaumarchais);

        final Map<Integer, Integer> expectedMap2 = Map.of(
            15, personOlgaMerson.getAge(),
            0, personLucyEarl.getAge(),
            1, personNicolasBeau.getAge(),
            3, personCyrillVirth.getAge(),
            4, personLucyEarl.getAge(),
            5, personAlexandreMerson.getAge(),
            7, personAnnetBeaumarchais.getAge()
        );

        final Map<Integer, Human> nullsOmlyMap = new HashMap<>();
        nullsOmlyMap.put(7, null);
        nullsOmlyMap.put(17, null);
        nullsOmlyMap.put(-77, null);

        return new Object[][] {
            { mapWithoutNull, expectedMap1 },
            { mapWithNulls, expectedMap2 },
            { Map.of(), Map.of() },
            { nullsOmlyMap, Map.of() }
        };
    }

    @Test(
        dataProvider = "getMapKeyToAge_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static <T extends Human> void getMapKeyToAge_ThrowsIllegalArgumentException_Test(
        final Map<Integer, T> map
    ) {
        getMapKeyToAge(map);
    }

    @DataProvider
    public static Object[][] getMapKeyToAge_ThrowsIllegalArgumentException_Data() {
        return new Object[][] { { null } };
    }

    // test for task 6.10
    @Test(dataProvider = "getMapAgeToListOfPeopleOfThisAge_PositiveCase_Data")
    public static <T extends Human> void getMapAgeToListOfPeopleOfThisAge_PositiveCase_Test(
        final Set<T> givenPeopleSet,
        final Map<Integer, List<T>> expectedMap
    ) {
        final Map<Integer, List<T>> actualMap = getMapAgeToListOfPeopleOfThisAge(givenPeopleSet);
        assertEquals(actualMap.keySet(), expectedMap.keySet());
        for (int key : actualMap.keySet()) {
            assertTrue(actualMap.get(key).containsAll(expectedMap.get(key)) && expectedMap.get(key).containsAll(actualMap.get(key)));
        }
    }

    @DataProvider
    public static Object[][] getMapAgeToListOfPeopleOfThisAge_PositiveCase_Data() {
        final Set<Human> differentAgePeople = Set.of(
            personNicolasBeau,
            personAnnetBeaumarchais,
            personLucyEarl,
            personLucyVirth,
            personLucyGreen,
            personLucyBrown,
            personJulieVirth,
            personAlexandreMerson,
            personCyrillVirth,
            personOlgaMerson
        );
        final List<Human> peopleAge10 = List.of(personNicolasBeau, personAnnetBeaumarchais);
        final List<Human> peopleAge20 = List.of(personJulieVirth, personAlexandreMerson);
        final List<Human> peopleAge22 = List.of(personLucyEarl, personLucyVirth, personLucyGreen, personLucyBrown);
        final List<Human> peopleAge46 = List.of(personCyrillVirth, personOlgaMerson);

        final Map<Integer, List<Human>> map = Map.of(
            personNicolasBeau.getAge(), peopleAge10,
            personJulieVirth.getAge(), peopleAge20,
            personLucyEarl.getAge(), peopleAge22,
            personOlgaMerson.getAge(), peopleAge46
        );

        final Set<Human> sameAgePeople = Set.of(
            personLucyEarl,
            personLucyVirth,
            personLucyGreen,
            personLucyBrown
        );

        return new Object[][] {
            { differentAgePeople, map },
            { sameAgePeople, Map.of(personLucyEarl.getAge(), List.of(personLucyEarl, personLucyVirth, personLucyGreen, personLucyBrown)) },
            { Set.of(), Map.of() },
        };
    }

    @Test(
        dataProvider = "getMapAgeToListOfPeopleOfThisAge_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static <T extends Human> void getMapAgeToListOfPeopleOfThisAge_ThrowsIllegalArgumentException_Test(
        final Set<T> givenPeopleSet
    ) {
        getMapAgeToListOfPeopleOfThisAge(givenPeopleSet);
    }

    @DataProvider
    public static Object[][] getMapAgeToListOfPeopleOfThisAge_ThrowsIllegalArgumentException_Data() {
        return new Object[][] { { null } };
    }

    // 6.6
    @Test(dataProvider = "getSortedByFullNameList_PositiveCase_Data")
    public static <T extends Human> void getSortedByFullNameList_PositiveCase_Test(
        final Set<T> givenPeopleSet,
        final List<T> sortedByFullNamePeopleList
    ) {
        final List<T> actualPeopleList = getSortedByFullNameList(givenPeopleSet);
        assertEquals(actualPeopleList, sortedByFullNamePeopleList);
    }

    @DataProvider
    public static Object[][] getSortedByFullNameList_PositiveCase_Data() {
        final Set<Human> givenHumansSet = Set.of(
            personLucyBrown,
            personLucyVirth,
            personAlexandreMerson,
            personOlgaMerson,
            personLucyEarl
        );
        final List<Human> expectedSortedHumansList = List.of(
            personLucyBrown,
            personLucyEarl,
            personAlexandreMerson,
            personOlgaMerson,
            personLucyVirth
        );

        final Set<Human> givenStudentsSet = Set.of(
            studentLucyBrown,
            studentLucyVirth,
            studentAlexandreMerson,
            studentOlgaMerson,
            studentLucyEarl
        );
        final List<Human> expectedSortedStudentsList = List.of(
            studentLucyBrown,
            studentLucyEarl,
            studentAlexandreMerson,
            studentOlgaMerson,
            studentLucyVirth
        );

        return new Object[][] {
            { givenHumansSet, expectedSortedHumansList },
            { givenStudentsSet, expectedSortedStudentsList },
            { Set.of(), List.of() },
            { Set.of(studentAlexandreMerson), List.of(studentAlexandreMerson) },
        };
    }

    // 6.11
    @Test(dataProvider = "getMappingAgeToMappingLetterToPeopleList_PositiveCase_Data")
    public static <T extends Human> void getMappingAgeToMappingLetterToPeopleList_PositiveCase_Test(
        final Set<T> people,
        final Map<Integer, Map<Character, List<T>>> expectedMap
    ) {
        final Map<Integer, Map<Character, List<T>>> actualMap = getMappingAgeToMappingLetterToPeopleList(people);
        assertEquals(actualMap, expectedMap);
    }

    @DataProvider
    public static Object[][] getMappingAgeToMappingLetterToPeopleList_PositiveCase_Data() {
        final int FIRST_AGE = personNicolasBeau.getAge();
        final int SECOND_AGE = personLucyEarl.getAge();
        final int THIRD_AGE  = personAlexandreMerson.getAge();
        final int FOURTH_AGE = personOlgaMerson.getAge();

        final Set<Human> givenHumansSetWithDifferentAgePeopleWithoutNull = Set.of(
            personNicolasBeau,
            personAnnetBeaumarchais,
            ///////////////////////
            personAlexBabington,
            personLukeBrown,
            personLucyBrown,
            personLucyEarl,
            personArielGreen,
            personLucyGreen,
            personLucyVirth,
            ///////////////////////
            personPierreVeron,
            personJulieVirth,
            personAlexandreMerson,
            ///////////////////////
            personCyrillVirth,
            personOlgaMerson
        );

        final Set<Human> givenHumansSetWithDifferentAgePeopleWithNulls = new HashSet<>();
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personNicolasBeau);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personAnnetBeaumarchais);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personAlexBabington);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personLukeBrown);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personLucyBrown);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personLucyEarl);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personArielGreen);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personLucyGreen);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personLucyVirth);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personPierreVeron);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personJulieVirth);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personAlexandreMerson);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personCyrillVirth);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(null);
        givenHumansSetWithDifferentAgePeopleWithNulls.add(personOlgaMerson);

        final Map<Integer, Map<Character, List<Human>>> expectedMapAgeToMapCharToHumanList_1 = Map.of(
            FIRST_AGE, Map.of(
                'B', List.of(personNicolasBeau, personAnnetBeaumarchais)
            ),
            SECOND_AGE, Map.of(
                'B', List.of(personAlexBabington, personLucyBrown, personLukeBrown),
                'E', List.of(personLucyEarl),
                'G', List.of(personArielGreen, personLucyGreen),
                'V', List.of(personLucyVirth)
            ),
            THIRD_AGE, Map.of(
                'M', List.of(personAlexandreMerson),
                'V', List.of(personPierreVeron, personJulieVirth)
            ),
            FOURTH_AGE, Map.of(
                'M', List.of(personOlgaMerson),
                'V', List.of(personCyrillVirth)
            )
        );

        final Set<Human> givenHumansSetWithSameAgePeople = Set.of(
            personAlexBabington,
            personLukeBrown,
            personLucyBrown,
            personLucyEarl,
            personArielGreen,
            personLucyGreen,
            personLucyVirth
        );
        final Map<Integer, Map<Character, List<Human>>> expectedMapAgeToCharToHumanList_2 = Map.of(
            SECOND_AGE, Map.of(
                'B', List.of(personAlexBabington, personLucyBrown, personLukeBrown),
                'E', List.of(personLucyEarl),
                'G', List.of(personArielGreen, personLucyGreen),
                'V', List.of(personLucyVirth)
            )
        );

        final Set<Student> givenStudentSetWithDifferentAgePeople = Set.of(
            studentAlexBabington,
            studentLucyBrown,
            studentLukeBrown,
            studentLucyEarl,
            studentArielGreen,
            studentLucyGreen,
            studentLucyVirth,
            ///////////////////////
            studentPierreVeron,
            studentJulieVirth,
            studentAlexandreMerson,
            ///////////////////////
            studentCyrillVirth,
            studentOlgaMerson
        );
        final Map<Integer, Map<Character, List<Student>>> expectedMapAgeToMapCharToStudentList_1 = Map.of(
            SECOND_AGE, Map.of(
                'B', List.of(studentAlexBabington, studentLucyBrown, studentLukeBrown),
                'E', List.of(studentLucyEarl),
                'G', List.of(studentArielGreen, studentLucyGreen),
                'V', List.of(studentLucyVirth)
            ),
            THIRD_AGE, Map.of(
                'M', List.of(studentAlexandreMerson),
                'V', List.of(studentPierreVeron, studentJulieVirth)
            ),
            FOURTH_AGE, Map.of(
                'M', List.of(studentOlgaMerson),
                'V', List.of(studentCyrillVirth)
            )
        );

        return new Object[][] {
            { givenHumansSetWithDifferentAgePeopleWithoutNull, expectedMapAgeToMapCharToHumanList_1 },
            { givenHumansSetWithDifferentAgePeopleWithNulls, expectedMapAgeToMapCharToHumanList_1 },
            { givenHumansSetWithSameAgePeople, expectedMapAgeToCharToHumanList_2 },
            { Set.of(new Human[] {}), Map.of() },
            { givenStudentSetWithDifferentAgePeople, expectedMapAgeToMapCharToStudentList_1 },
            { Set.of(new Student[] {}), Map.of() },
        };
    }

    @Test(
        dataProvider = "getMappingAgeToMappingLetterToPeopleList_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static <T extends Human> void getMappingAgeToMappingLetterToPeopleList_ThrowsIllegalArgumentException_Test(
        final Set<T> people
    ) {
        getMappingAgeToMappingLetterToPeopleList(people);
    }
    @DataProvider
    public static Object[][] getMappingAgeToMappingLetterToPeopleList_ThrowsIllegalArgumentException_Data() {
        return new Object[][] { { null } };
    }
}
