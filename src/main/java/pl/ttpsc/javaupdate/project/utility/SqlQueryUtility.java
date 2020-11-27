package pl.ttpsc.javaupdate.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.exception.SqlQueryUtilityException;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.SearchValue;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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

    private static String extractTableName(Persistable persistable) {
        return persistable.getClass().getSimpleName().toLowerCase() + "s ";
    }

    private static String extractFieldsNames(Persistable persistable) {
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

    private static String extractFieldsValues(Persistable persistable) {
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
                    } else if (isFieldGivenType(fieldTypeName, "Character[]")) {
                        logger.debug("password: " + Arrays.toString((Character[])object));
                        query.append("'")
                                .append(charArrayToString((Character[])object))
                                .append("'")
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

    private static String charArrayToString(Character[] characters) {
        StringBuilder result = new StringBuilder();
        for (Character character : characters) {
            result.append(character);
        }
        return result.toString();
    }

    private static boolean isFieldGivenType(String fieldType, String type) {
        return fieldType.toLowerCase().contains(type.toLowerCase());
    }

    public static String generateDeleteQuery(Class<?> table, int id) {
        String tableName = table.getSimpleName().toLowerCase();
        logger.debug("Delete Query: DELETE FROM " + tableName + "s WHERE id=" + id);
        return "DELETE FROM " + tableName + "s WHERE id=" + id + ";";
    }

    private static String querySpecToSql(QuerySpec querySpec) {
        List<Object> specs = querySpec.getSpecs();
        List<String> values = new ArrayList<>();
        for (Object spec : specs) {
            if (spec instanceof SearchValue) {
                if (((SearchValue) spec).getType().equals(String.class)) {
                    values.add("'" + ((SearchValue) spec).getValue() + "'");
                } else {
                    values.add(((SearchValue) spec).getValue().toString());
                }
            } else {
                values.add(spec.toString());
            }
        }
        logger.debug("QuerySpecToSql list: " + specs.toString());
        return String.join(" ", values);
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

    public static String generateUpdateQuery(Persistable persistable) throws SqlQueryUtilityException {
        try {
            Field[] fields = persistable.getClass().getDeclaredFields();
            List<String> params = new ArrayList<>();
            String id = "";

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(persistable);
                if (fieldName.equals("id")) {
                    id = String.valueOf(value);
                }
                String fieldTypeName = field.getType().getTypeName();

                if (!fieldName.equals("id") && !fieldName.equals("creator")) {
                    if (isFieldGivenType(fieldTypeName, "String")) {
                        params.add(fieldName + "='" + value + "'");
                    } else if (isFieldGivenType(fieldTypeName, "int")) {
                        params.add(fieldName + "=" + value);
                    } else if (isFieldGivenType(fieldTypeName, "boolean")) {
                        params.add(fieldName + "=" + String.valueOf(value).toUpperCase());
                    }
                }
            }

            logger.debug("Update query: " + "UPDATE " + extractTableName(persistable)
                    + " SET " + String.join(", ", params)
                    + " WHERE id=" + id + ";");

            return "UPDATE " + extractTableName(persistable)
                    + " SET " + String.join(", ", params)
                    + " WHERE id=" + id + ";";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new SqlQueryUtilityException();
    }
}
