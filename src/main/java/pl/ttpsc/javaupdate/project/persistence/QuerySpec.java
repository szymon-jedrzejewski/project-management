package pl.ttpsc.javaupdate.project.persistence;

import java.util.List;

public class QuerySpec {

    private Class<?> tableName;
    private List<SearchCondition> conditions;

    public QuerySpec() {
    }

    public Class<?> getTableName() {
        return tableName;
    }

    public void setTableName(Class<?> tableName) {
        this.tableName = tableName;
    }

    public List<SearchCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<SearchCondition> conditions) {
        this.conditions = conditions;
    }

    public void addCondition(SearchCondition condition) {
        conditions.add(condition);
    }

    @Override
    public String toString() {
        return "QuerySpec{" +
                "tableName='" + tableName + '\'' +
                ", conditions=" + conditions +
                '}';
    }
}