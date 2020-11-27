package pl.ttpsc.javaupdate.project.persistence;

public class SearchValue {

    private final Class<?> clazz;
    private final Object value;

    public SearchValue(Class<?> type, Object value) {
        this.clazz = type;
        this.value = value;
    }

    public Class<?> getType() {
        return clazz;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SearchValue{" +
                "type=" + clazz +
                ", value=" + value +
                '}';
    }
}
