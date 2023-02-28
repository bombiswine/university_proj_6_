package org.lab_task_1;

import org.lab_task_1.human.Human;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static org.lab_task_1.CollectionsDemo.countStringStartsWith;
import static org.lab_task_1.CollectionsDemo.getPeopleWithIdentifiersFromGivenSet;
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
        List<Human>  allPeople = List.of(
            personLucyEarl,
            personAlexandreMerson,
            personCyrillVirth,
            personLucyBrown,
            personOlgaMerson,
            personJulieVirth,
            personLucyVirth,
            personLucyGreen
        );

        Set<Integer> evenKeysSet = new TreeSet<>(List.of(0, 2, 4, 6));
        List<Human>  peopleWithEvenKeys = List.of(
            personLucyEarl,
            personCyrillVirth,
            personOlgaMerson,
            personLucyVirth
        );

        Set<Integer> randomKeysSet = new TreeSet<>(List.of(0, 12, 1, 7, -5, 32, -13));
        List<Human>  peopleWithKeysFromRandomKeysSet = List.of(
            personLucyEarl,
            personAlexandreMerson,
            personLucyGreen
        );

        Set<Integer> notPresentedKeysSet = new TreeSet<>(List.of(12, -21, 17, -5, 32, -13));

        Map<Integer, Human> mapWithoutNull = new TreeMap<>();
        for (int key : allKeysSet) {
            mapWithoutNull.put(key, allPeople.get(key));
        }

        return new Object[][] {
            { mapWithoutNull, allKeysSet, new TreeSet<>(mapWithoutNull.values()) },
            { mapWithoutNull, evenKeysSet, new TreeSet<>(peopleWithEvenKeys) },
            { mapWithoutNull, randomKeysSet, new TreeSet<>(peopleWithKeysFromRandomKeysSet) },
            { mapWithoutNull, notPresentedKeysSet, new TreeSet<>() },
            { mapWithoutNull, new TreeSet<>(), new TreeSet<>() },
            { new TreeMap<>(), allKeysSet, new TreeSet<>() },
            { new TreeMap<>(), evenKeysSet, new TreeSet<>() },
            { new TreeMap<>(), randomKeysSet, new TreeSet<>() },
            { new TreeMap<>(), new TreeSet<>(), new TreeSet<>() },

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
        Set<Integer> allKeysSet = new TreeSet<>(List.of(0, 1, 2, 3, 4, 5, 6, 7));
        List<Human>  listWithNulls = Arrays.asList(
            personLucyEarl,
            null,
            personCyrillVirth,
            null,
            personLucyBrown,
            personOlgaMerson,
            null,
            personLucyGreen
        );

        Map<Integer, Human> mapWithNulls = new TreeMap<>();
        for (int key : allKeysSet) {
            mapWithNulls.put(key, listWithNulls.get(key));
        }

        Map<Integer, Human> mapWithoutNull = new TreeMap<>();
        for (int key : allKeysSet) {
            if (listWithNulls.get(key) != null) {
                mapWithoutNull.put(key, listWithNulls.get(key));
            } else {
                mapWithoutNull.put(key, personLucyEarl);
            }
        }

        return new Object[][] {
            { mapWithNulls, allKeysSet },
            { null, allKeysSet },
            { mapWithoutNull, null },
        };
    }
}
