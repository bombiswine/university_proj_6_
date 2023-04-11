package seminar_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataSet implements Iterable<Data> {
    private final List<Data> dataList;

    public DataSet() {
        dataList = new ArrayList<>(0);
    }

    public void pushBack(final Data element) {
        if (element == null) {
            throw new IllegalArgumentException(
                "The null-ref passed into DataSet.pushBack(Data)"
            );
        }
        dataList.add(element);
    }

    @Override
    public Iterator<Data> iterator() {
        return new Iterator<>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < dataList.size();
            }
            @Override
            public Data next() {
                if (index >= dataList.size()) {
                    throw new ArrayIndexOutOfBoundsException(
                        "Invalid index: " + index
                    );
                }
                return dataList.get(index++);
            }
        };
    }
}
