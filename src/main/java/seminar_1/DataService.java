package seminar_1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataService {
    public static List<Data> getNamesakesList(
        final List<Data> dataList,
        final String     name
    ) {
        List<Data> nameSakes = new LinkedList<>();
        if (dataList != null) {
            for (Data datum : dataList) {
                if (datum != null && datum.getName().equals(name)) {
                    nameSakes.add(datum);
                }
            }
        }

        return nameSakes;
    }

    public static List<Data> getDataWithValueNotGreaterThanGivenBound(
        final List<Data> dataList,
        final double level
    ) {
        if (dataList == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getDataWithValueNotGreaterThanGivenBound as dataList\n"
            );
        }
        List<Data> resultList = new LinkedList<>();
        for (Data datum : dataList) {
            if (datum != null && Double.compare(datum.getValue(), level) <= 0) {
                resultList.add(datum);
            }
        }

        return resultList;
    }

    public static List<Data> getDataWithNamesFromGivenNamesSet(
        final List<Data> dataList,
        final Set<String> namesSet
    ) {
        if (dataList == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getDataWithValueNotGreaterThanGivenBound as dataList\n"
            );
        }
        if (namesSet == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getDataWithValueNotGreaterThanGivenBound as namesSet\n"
            );
        }

        List<Data> resutList = new LinkedList<>();
        for (Data datum : dataList) {
            if (datum != null && namesSet.contains(datum.getName())) {
                resutList.add(datum);
            }
        }

        return resutList;
    }
}
