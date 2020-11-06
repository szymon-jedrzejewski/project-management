package pl.ttpsc.javaupdate.project.persistence;

public class SearchCondition {

    private String column;
    private Object value;

    public SearchCondition() {
    }

    public SearchCondition(String column, Object value) {
        this.column = column;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "column='" + column + '\'' +
                ", value=" + value +
                '}';
    }
}
