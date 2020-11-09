package pl.ttpsc.javaupdate.project.persistence;

public class SearchCondition {

    private String column;
    //TODO replace string operator with enum
    private String operator;
    private Object value;

    public SearchCondition() {
    }

    public SearchCondition(String column, String operator, Object value) {
        this.column = column;
        this.operator = operator;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "column='" + column + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
