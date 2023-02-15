package org.example;

import org.example.human.FullName;
import org.example.human.Human;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.ListDemo.getIndependentListWithoutGivenPerson;
import static org.example.ListDemo.getNamesakesList;
import static org.example.ListDemoTest.HumansTestData.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotSame;

public class ListDemoTest {
    static class HumansTestData {
        public final static Human LucyEarl = new Human(
            new FullName("Lucy", "", "Earl"),
            LocalDate.of(1998, 3, 12),
            "Female",
            "English",
            175,
            65
        );
        public final static Human LucyVirth = new Human(
            new FullName("Lucy", "", "Virth"),
            LocalDate.of(1998, 5, 10),
            "Female",
            "English",
            175,
            65
        );
        public final static Human AlexandreMerson = new Human(
            new FullName("Alexandre", "Igorevich", "Meson"),
            LocalDate.of(1998, 3, 12),
            "Male",
            "Russian",
            176,
            82
        );

        public final static Human OlgaMerson = new Human(
            new FullName("Olga", "Igorevich", "Meson"),
            LocalDate.of(1976, 12, 6),
            "Female",
            "Russian",
            155,
            70
        );

        public final static Human JulieVirth = new Human(
            new FullName("Julie", "", "Virth"),
            LocalDate.of(1998, 3, 12),
            "Female",
            "Swiss",
            179,
            79
        );
        public final static Human LucyBrown = new Human(
            new FullName("Lucy", "", "Brown"),
            LocalDate.of(1998, 3, 12),
            "Female",
            "English",
            175,
            65
        );

        public final static Human CyrillVirth = new Human(
            new FullName("Cyrill", "", "Virth"),
            LocalDate.of(1998, 3, 12),
            "Male",
            "Russian",
            180,
            90
        );
        public final static Human LucyGreen = new Human(
            new FullName("Lucy", "", "Green"),
            LocalDate.of(1998, 3, 12),
            "Female",
            "English",
            175,
            65
        );
    }

    @Test(dataProvider = "getNamesakesList_Data")
    public static void getNamesakesList_Test(
        List< Human > humans,
        Human person,
        List< Human > expectedList
    ) {
        List< Human > actualList = getNamesakesList(humans, person);
        assertEquals(actualList, expectedList);
    }

    @DataProvider
    public static Object[][] getNamesakesList_Data() {
        List< Human > humanList = List.of(LucyEarl, LucyVirth, AlexandreMerson, JulieVirth, LucyBrown);
        List< Human > expectedList1 = List.of(LucyVirth, JulieVirth);
        List< Human > expectedList2 = List.of(AlexandreMerson);
        List< Human > emptyList = new ArrayList<>(0);

        return new Object[][] {
            { humanList, CyrillVirth, expectedList1 },
            { humanList, OlgaMerson, expectedList2 },
            { humanList, LucyGreen, emptyList },
            { null, CyrillVirth, emptyList },
            { null, OlgaMerson, emptyList },
            { null, LucyGreen, emptyList },
            { emptyList, CyrillVirth, emptyList },
            { emptyList, OlgaMerson, emptyList },
            { emptyList, LucyGreen, emptyList },
        };
    }

    @Test(dataProvider = "getIndependentListWithoutGivenPerson_PositiveCase_Data")
    public static void getIndependentListWithoutGivenPerson_PositiveCase_Test(
        final List< Human > people,
        final Human person,
        final List< Human > expectedList
    ) {
        List< Human > actualList = getIndependentListWithoutGivenPerson(people, person);
        assertEquals(actualList, expectedList);
        assertNotSame(
            people.get(people.indexOf(LucyBrown)), actualList.get(actualList.indexOf(LucyBrown)));
    }

    @DataProvider
    public static Object[][] getIndependentListWithoutGivenPerson_PositiveCase_Data() {
        List< Human > initialList           = List.of(LucyEarl, LucyVirth, AlexandreMerson, JulieVirth, LucyBrown);
        List< Human > initialListWithNull   = Arrays.asList(LucyEarl, null, AlexandreMerson, JulieVirth, LucyBrown);
        List< Human > expectedList1         = List.of(LucyEarl, LucyVirth, JulieVirth, LucyBrown);
        List< Human > expectedList1WithNull = Arrays.asList(LucyEarl, null, JulieVirth, LucyBrown);
        List< Human > expectedList2         = List.of(LucyVirth, AlexandreMerson, JulieVirth, LucyBrown);
        List< Human > expectedList2WithNull = Arrays.asList(null, AlexandreMerson, JulieVirth, LucyBrown);

        return new Object[][] {
            { initialList, AlexandreMerson, expectedList1 },
            { initialListWithNull, AlexandreMerson, expectedList1WithNull },
            { initialList, LucyEarl, expectedList2 },
            { initialListWithNull, LucyEarl, expectedList2WithNull },
            { initialList, OlgaMerson, initialList },
            { initialListWithNull, OlgaMerson, initialListWithNull },
        };
    }

}
