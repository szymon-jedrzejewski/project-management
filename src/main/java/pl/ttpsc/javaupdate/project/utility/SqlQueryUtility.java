package pl.ttpsc.javaupdate.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class SqlQueryUtility {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    private SqlQueryUtility() {
    }

    public static String extractTableName(Persistable persistable) {
        return persistable.getClass().getSimpleName().toLowerCase() + "s ";
    }

    public static String generateFindQuery(QuerySpec querySpec) {
        String tableName = querySpec.getTableName().getSimpleName().toLowerCase();
        if (querySpec.getQuery() == null || querySpec.getQuery().isEmpty()) {
            logger.debug("Find query: " + "SELECT * FROM " + tableName + "s;");
            return "SELECT * FROM " + tableName + "s;";
        }

        logger.debug("SELECT * FROM " + tableName + "s " + querySpec.getQuery() + ";");
        return "SELECT * FROM " + tableName + "s " + querySpec.getQuery() + ";";
    }

    public static String generateInsertQuery(Persistable persistable) {
        return "INSERT INTO "
                + SqlQueryUtility.extractTableName(persistable)
                + "(" + SqlQueryUtility.extractFieldsNames(persistable) + ") "
                + "VALUES (" + SqlQueryUtility.extractFieldsValues(persistable) + ");";
    }

    public static String extractFieldsNames(Persistable persistable) {
        Field[] fields = persistable.getClass().getDeclaredFields();
        List<String> names = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                names.add(field.getName().toLowerCase());
            }
        }

        return String.join(", ", names);
    }

    public static String extractFieldsValues(Persistable persistable) {
        Field[] fields = persistable.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            logger.debug("Field type: " + field.getType());
            if (!field.getName().equals("id")) {
                try {
                    Object object = field.get(persistable);
                    String fieldTypeName = field.getType().getTypeName();
                    logger.debug("Field value: " + object);
                    logger.debug("Integer: " + isFieldGivenType(fieldTypeName, "int"));
                    if (isFieldGivenType(fieldTypeName, "String")) {
                        query.append("'")
                                .append(object)
                                .append("'")
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "boolean")) {
                        query.append(String.valueOf(object).toUpperCase())
                                .append(", ");
                    } else if (isFieldGivenType(fieldTypeName, "int")) {
                        query.append(object)
                                .append(", ");
                    }

                } catch (IllegalAccessException e) {
                    logger.error("Field not found");
                    logger.error(e.getMessage());
                }
            }
        }

        logger.debug("Fields values: " + query);
        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }

    private static boolean isFieldGivenType(String fieldType, String type) {
        return fieldType.toLowerCase().contains(type.toLowerCase());
    }

    public static String generateDeleteQuery(QuerySpec qs){
        logger.debug("Delete Query: " +"DELETE FROM " + qs.getTableName().getSimpleName().toLowerCase() + "s " + qs.getQuery());
        return "DELETE FROM " + qs.getTableName().getSimpleName().toLowerCase() + "s " + qs.getQuery();
    }
}
