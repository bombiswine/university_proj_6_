package seminar_1;

import java.util.Objects;

public class Data {
    private final String name;
    private final double value;

    public Data(final String name, final double value) {
        if ("".equals(name)) {
            throw new IllegalArgumentException(
                "The null-ref or an empty string passed as 'name' to Data constructor"
            );
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        return Double.compare(data.value, value) == 0 && name.equals(data.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
