package seminar_1;

import java.util.HashSet;
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

        final List<Data> resutList = new LinkedList<>();
        for (Data datum : dataList) {
            if (datum != null && namesSet.contains(datum.getName())) {
                resutList.add(datum);
            }
        }

        return resutList;
    }

    public static String[] getDataWithPositiveValue(final List<Data> dataList) {
        if (dataList == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getDataWithPositiveValue as dataList" + System.lineSeparator()
            );
        }
        final List<String> resultList = new LinkedList<>();
        for (Data datum : dataList) {
            if (datum != null && Double.compare(datum.getValue(), 0.0) > 0) {
                resultList.add(datum.getName());
            }
        }

        return resultList.toArray(new String[ 0 ]);
    }

    public static <T> Set<T> getUnionOf(final List<Set<T>> sets) {
        if (sets == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getUnionOf as List<Set<T>> sets" + System.lineSeparator()
            );
        }
        final Set<T> union = new HashSet<>();
        for (Set<T> set : sets) {
            if (set != null) {
                union.addAll(set);
            }
        }

        return union;
    }
    public static <T> Set<T> getIntersectionOf(final List<Set<T>> sets) {
        if (sets == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getIntersectionOf as List<Set<T>> sets" + System.lineSeparator()
            );
        }
        final Set<T> intersection = new HashSet<>();
        final Set<T> union = getUnionOf(sets);
        boolean belongsIntersection = true;

        for (T element : union) {
            for (Set<T> set : sets) {
                if (set != null && !set.contains(element)) {
                    belongsIntersection = false;
                    break;
                }
            }
            if (belongsIntersection) {
                intersection.add(element);
            }
        }

        return intersection;
    }

    public static <T> List<Set<T>> getSetsOfMaxCard(final List<Set<T>> sets) {
        if (sets == null) {
            throw new IllegalArgumentException(
                "The null-ref passed to getSetsOfMaxCard as List<Set<T>> sets" + System.lineSeparator()
            );
        }
        int maxCard = 0;
        for (Set<T> set : sets) {
            if (maxCard < set.size()) {
                maxCard = set.size();
            }
        }

        final List<Set<T>> maxCardSets = new LinkedList<>();
        for (Set<T> set : sets) {
            if (set.size() == maxCard) {
                maxCardSets.add(set);
            }
        }

        return maxCardSets;
    }



}
