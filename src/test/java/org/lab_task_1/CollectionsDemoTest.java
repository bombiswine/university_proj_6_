package org.lab_task_1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lab_task_1.CollectionsDemo.countStringStartsWith;
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
}
