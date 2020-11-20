package pl.ttpsc.javaupdate.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class SqlQueryUtility {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    private SqlQueryUtility() {
    }

    public static String findQueryOf(QuerySpec querySpec) {
        String tableName = querySpec.getTableName().getSimpleName().toLowerCase();
        if (querySpec.getSpecs() == null || querySpec.getSpecs().isEmpty()) {
            logger.debug("Find query: " + "SELECT * FROM " + tableName + "s;");
            return "SELECT * FROM " + tableName + "s;";
        }

        logger.debug("SELECT * FROM " + tableName + "s " + querySpecToSql(querySpec) + ";");
        return "SELECT * FROM " + tableName + "s " + querySpecToSql(querySpec) + ";";
    }

    public static String createQueryOf(Persistable persistable) {
        return "INSERT INTO "
                + SqlQueryUtility.extractTableName(persistable)
                + "(" + SqlQueryUtility.extractFieldsNames(persistable) + ") "
                + "VALUES (" + SqlQueryUtility.extractFieldsValues(persistable) + ");";
    }

    public static String extractTableName(Persistable persistable) {
        return persistable.getClass().getSimpleName().toLowerCase() + "s ";
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
                    logger.error("IllegalAccessException: " + e.getMessage());
                }
            }
        }

        logger.debug("Fields values: " + query);
        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }

    private static boolean isFieldGivenType(String fieldType, String type) {
        return fieldType.toLowerCase().contains(type.toLowerCase());
    }

    public static String generateDeleteQuery(String tableName, int id) {
        logger.debug("Delete Query: DELETE FROM " + tableName.toLowerCase() + "s WHERE id=" + id );
        return "DELETE FROM " + tableName.toLowerCase() + "s WHERE id=" + id;
    }

    private static String querySpecToSql(QuerySpec querySpec) {
        List<Object> specs = querySpec.getSpecs();
        StringBuilder query = new StringBuilder();
        final int INDEX_OF_VALUE = 3;
        for (Object spec : specs) {
            if (specs.indexOf(spec) == INDEX_OF_VALUE) {
                query.append("'")
                        .append(spec)
                        .append("'");
            } else {
                query.append(spec);
                query.append(" ");
            }
        }
        return query.toString();
    }

    public static List<Persistable> resultSetToPersistable(PreparedStatement preparedStatement, QuerySpec querySpec)
            throws NoSuchMethodException, SQLException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        List<Persistable> persistables = new ArrayList<>();
        Constructor<?> constructor = querySpec.getTableName().getDeclaredConstructor();
        ResultSet result = preparedStatement.executeQuery();

        while (result.next()) {

            Persistable persistable = (Persistable) constructor.newInstance();
            logger.debug("Created object: " + persistable);
            Field[] fields = persistable.getClass().getDeclaredFields();

            for (Field field : fields) {

                field.setAccessible(true);
                logger.debug("Column name: " + field.getName());
                logger.debug("Value: " + result.getObject(field.getName()));
                field.set(persistable, result.getObject(field.getName()));
            }

            persistables.add(persistable);
        }
        return persistables;
    }
}
