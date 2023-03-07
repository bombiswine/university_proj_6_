package org.lab_task_1;

import org.lab_task_1.human.Human;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.lab_task_1.CollectionsDemo.*;
import static org.lab_task_1.DataForTesting.*;
import static org.lab_task_1.DataForTesting.personLucyBrown;
import static org.testng.Assert.assertEquals;

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
        assertEquals(expectedPeopleSet, actualPeopleSet);
    }

    @DataProvider
    public static Object[][] getPeopleWithIdentifiersFromGivenSet_PositiveCase_Data() {
        Set<Integer> allKeysSet = new TreeSet<>(List.of(0, 1, 2, 3, 4, 5, 6, 7));
        Set<Integer> evenKeysSet = new TreeSet<>(List.of(0, 2, 4, 6));
        Set<Integer> randomKeysSet = new TreeSet<>(List.of(0, 12, 1, 7, -5, 32, -13));
        Set<Integer> notPresentedKeysSet = new TreeSet<>(List.of(12, -21, 17, -5, 32, -13));

        Set<Human>  peopleWithEvenKeys = Set.of(
            personLucyEarl,
            personCyrillVirth,
            personOlgaMerson,
            personLucyVirth
        );
        Set<Human>  peopleWithKeysFromRandomKeysSet = Set.of(
            personLucyEarl,
            personAlexandreMerson,
            personLucyGreen
        );
        Set<Human> nonNullPeopleSet = Set.of(
            personAlexandreMerson,
            personLucyBrown,
            personLucyGreen,
            personOlgaMerson
        );

        Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(0, personLucyBrown);
        mapWithNulls.put(1, personAlexandreMerson);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personLucyBrown);
        mapWithNulls.put(4, personLucyGreen);
        mapWithNulls.put(5, null);
        mapWithNulls.put(6, personOlgaMerson);
        mapWithNulls.put(7, null);

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
        Set<Integer> allKeysSet          = Set.of(0, 1, 2, 3);
        Set<Integer> randomKeysSet       = Set.of(0, 12, 1, 7, -5, 32, -13);
        Set<Integer> notPresentedKeysSet = Set.of(12, -21, 17, -5, 32, -13);

        Map<Integer, Human> mapWithAdultsAndChildren = Map.of(
            0, personLucyEarl,
            1, personSmallBoy,
            2, personAlexandreMerson,
            3, personSmallGirl
        );
        Map<Integer, Human> mapWithAdultsOnly = Map.of(
            0, personLucyEarl,
            1, personAlexandreMerson,
            2, personCyrillVirth,
            3, personLucyGreen
        );
        Map<Integer, Human> mapWithChildrenOnly = Map.of(
            0, personSmallBoy,
            1, personSmallGirl,
            2, personSmallBoy,
            3, personSmallGirl
        );

        Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(0, personLucyEarl);
        mapWithNulls.put(1, personSmallBoy);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personCyrillVirth);
        mapWithNulls.put(4, personLucyEarl);
        mapWithNulls.put(5, personAlexandreMerson);
        mapWithNulls.put(6, null);
        mapWithNulls.put(7, personSmallGirl);

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
        Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personSmallBoy,
            2, personAlexandreMerson,
            3, personSmallGirl
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
        Map<Integer, Human> mapWithoutNull = Map.of(
            0, personLucyEarl,
            1, personSmallBoy,
            2, personAlexandreMerson,
            3, personSmallGirl
        );
        Map<Integer, Integer> expectedMap1 = Map.of(
            0, personLucyEarl.getAge(),
            1, personSmallBoy.getAge(),
            2, personAlexandreMerson.getAge(),
            3, personSmallGirl.getAge()
        );

        Map<Integer, Human> mapWithNulls = new HashMap<>();
        mapWithNulls.put(15, personOlgaMerson);
        mapWithNulls.put(0, personLucyEarl);
        mapWithNulls.put(1, personSmallBoy);
        mapWithNulls.put(2, null);
        mapWithNulls.put(3, personCyrillVirth);
        mapWithNulls.put(4, personLucyEarl);
        mapWithNulls.put(5, personAlexandreMerson);
        mapWithNulls.put(6, null);
        mapWithNulls.put(7, personSmallGirl);

        Map<Integer, Integer> expectedMap2 = Map.of(
            15, personOlgaMerson.getAge(),
            0, personLucyEarl.getAge(),
            1, personSmallBoy.getAge(),
            3, personCyrillVirth.getAge(),
            4, personLucyEarl.getAge(),
            5, personAlexandreMerson.getAge(),
            7, personSmallGirl.getAge()
        );

        Map<Integer, Human> nullsOmlyMap = new HashMap<>();
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
            assertEquals(Set.of(actualMap.get(key)), Set.of(expectedMap.get(key)));
        }
    }

    @DataProvider
    public static Object[][] getMapAgeToListOfPeopleOfThisAge_PositiveCase_Data() {
        final Set<Human> differentAgePeople = Set.of(
            personSmallBoy,
            personSmallGirl,
            personLucyEarl,
            personLucyVirth,
            personLucyGreen,
            personLucyBrown,
            personJulieVirth,
            personAlexandreMerson,
            personCyrillVirth,
            personOlgaMerson
        );
        final List<Human> peopleAge10 = List.of(personSmallBoy, personSmallGirl);
        final List<Human> peopleAge20 = List.of(personJulieVirth, personAlexandreMerson);
        final List<Human> peopleAge22 = List.of(personLucyEarl, personLucyVirth, personLucyGreen, personLucyBrown);
        final List<Human> peopleAge46 = List.of(personCyrillVirth, personOlgaMerson);

        final Map<Integer, List<Human>> map = Map.of(
            personSmallBoy.getAge(), peopleAge10,
            personJulieVirth.getAge(), peopleAge20,
            personLucyEarl.getAge(), peopleAge22,
            personOlgaMerson.getAge(), peopleAge46
        );

        return new Object[][] {
            { differentAgePeople, map },
        };
    }
}
