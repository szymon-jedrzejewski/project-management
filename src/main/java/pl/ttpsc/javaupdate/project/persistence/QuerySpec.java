package pl.ttpsc.javaupdate.project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class QuerySpec {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private Class<?> tableName;
    private final List<Object> specs = new ArrayList<>();

    public QuerySpec() {
    }

    public QuerySpec(Class<?> tableName) {
        this.tableName = tableName;
    }

    public Class<?> getTableName() {
        return tableName;
    }

    public void setTableName(Class<?> tableName) {
        this.tableName = tableName;
    }

    public List<Object> getSpecs() {
        return specs;
    }

    public void append(QueryOperator queryOperator, SearchCondition searchCondition) {

        specs.add(queryOperator.toString());
        specs.add(searchCondition.getColumn());
        specs.add(searchCondition.getOperator().toString());
        specs.add(searchCondition.getValue());

        logger.debug("Specs: " + specs);
    }
}