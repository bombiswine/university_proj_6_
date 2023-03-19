package seminar_1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static seminar_1.DataService.*;
import static seminar_1.DataServiceTest.DataServiceTestData.*;
import static org.testng.Assert.assertEquals;

public class DataServiceTest {
    static class DataServiceTestData {

        public final static Data lucyEarl  = new Data("Lucy", 1998.0);
        public final static Data lucySmith = new Data("Lucy", 1995.0);
        public final static Data alexandre = new Data("Alexandre", 2002.0);
        public final static Data liner     = new Data("Stabilo point 88", 2016.0);
        public final static Data curve     = new Data("Hyperbola", -1.5);
    }
    @Test(dataProvider = "getNamesakesList_Data")
    public static void getNamesakesList_Test(
        List<Data> dataList,
        String datumName,
        List<Data> expectedList
    ) {
        List<Data> actualList = getNamesakesList(dataList, datumName);
        assertEquals(actualList, expectedList);
    }
    @DataProvider
    public static Object[][] getNamesakesList_Data() {
        List<Data> dataList1 = List.of(lucyEarl, alexandre, lucySmith, liner, curve);
        List<Data> dataList2 = List.of(lucyEarl, lucyEarl, lucySmith);

        return new Object[][] {
            { dataList1, "Lucy", List.of(lucyEarl, lucySmith) },
            { dataList1, "lucy", List.of() },
            { dataList1, "Alexandre", List.of(alexandre) },
            { dataList1, "Parabola", List.of() },
            { dataList2, "Lucy", dataList2 },
            { null, "Parabola", List.of() },
        };
    }
    @Test(dataProvider = "getDataWithValueNotGreaterThanGivenBound_PositiveCase_Data")
    public static void getDataWithValueNotGreaterThanGivenBound_Test(
        final List<Data> dataList,
        final double level,
        final List<Data> expectedList
    ) {
        final List<Data> actualList = getDataWithValueNotGreaterThanGivenBound(dataList, level);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getDataWithValueNotGreaterThanGivenBound_PositiveCase_Data() {
        List<Data> dataList = List.of(lucyEarl, alexandre, lucySmith, liner, curve);

        return new Object[][] {
            { dataList, 3000.0, dataList },
            { dataList, -10.0, List.of() },
            { dataList, 2000.0, List.of(lucyEarl, lucySmith, curve) },
            { dataList, 0.0, List.of(curve) },
        };
    }

    @Test(dataProvider = "getDataWithNamesFromGivenNamesSet_PositiveCase_Data")
    public static void getDataWithNamesFromGivenNamesSet_PositiveCase_Test(
        final List<Data> dataList,
        final Set<String> namesSet,
        final List<Data> expectedList
    ) {
        List<Data> actualList = getDataWithNamesFromGivenNamesSet(dataList, namesSet);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getDataWithNamesFromGivenNamesSet_PositiveCase_Data() {
        List<Data> dataList   = List.of(lucyEarl, lucyEarl, alexandre, lucySmith, liner, lucyEarl, curve);
        Set<String> namesSet1 = Set.of("Lucy", "Stabilo point 88");
        Set<String> namesSet2 = Set.of("Stabilo point 88");
        Set<String> namesSet3 = Set.of("Alexandre", "Hyperbola", "Lucy", "Stabilo point 88");
        Set<String> namesSet4 = Set.of("Parabola");
        Set<String> namesSet5 = Set.of("Alexandre", "Parabola");
        Set<String> namesSet6 = Set.of();

        return new Object[][] {
            { dataList, namesSet1, List.of(lucyEarl, lucyEarl, lucySmith, liner, lucyEarl) },
            { dataList, namesSet2, List.of(liner) },
            { dataList, namesSet3, dataList },
            { dataList, namesSet4, List.of() },
            { dataList, namesSet5, List.of(alexandre) },
            { dataList, namesSet6, List.of() },
        };
    }
}


