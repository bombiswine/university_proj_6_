package seminar_1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class DataSetTest {
    @Test(dataProvider = "checkWhetherDataSetIsIterable_Data")
    public static void checkWhetherDataSetIsIterable_Test(
        final DataSet givenDataSet
    ) {
        final List<Data> dataList = new LinkedList<>();
        for (Data dt : givenDataSet) {
             dataList.add(dt);
        }
    }

    @DataProvider
    public static Object[][] checkWhetherDataSetIsIterable_Data() {
        final DataSet dataSet = new DataSet();
        dataSet.pushBack(new Data("Aname", 1));
        dataSet.pushBack(new Data("Bname", 2));
        dataSet.pushBack(new Data("Cname", 3));

        return new Object[][] {
            { dataSet }
        };
    }
}
