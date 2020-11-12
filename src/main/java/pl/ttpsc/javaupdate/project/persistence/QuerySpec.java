package pl.ttpsc.javaupdate.project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

public class QuerySpec {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private Class<?> tableName;
    private String query = "";

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

    public String getQuery() {
        return query;
    }

    private void tableNameToString(Class<?> tableName) {
        if (tableName != null) {
            query = tableName.getSimpleName().toLowerCase() + "s ";
        }
    }
    public void append(QueryOperator queryOperator, SearchCondition searchCondition) {
        logger.debug("QuerySpec append query: " + query);

        query += queryOperator.toString()
                + " "
                + searchCondition.getColumn()
                + searchCondition.getOperator().toString()
                + searchCondition.getValue();
    }
}