package pl.ttpsc.javaupdate.project.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class SqlQueryUtility {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    private SqlQueryUtility() {
    }

    public static String generateTableName(Persistable persistable) {
        return persistable.getClass().getSimpleName().toLowerCase() + "s ";
    }

    public static String getFullQuery(Persistable persistable) {
        return "INSERT INTO "
                + SqlQueryUtility.generateTableName(persistable)
                + "(" + SqlQueryUtility.generateFieldsNames(persistable) + ") "
                + "VALUES (" + SqlQueryUtility.generateFieldsValues(persistable) + ");";
    }

    public static String generateFieldsNames(Persistable persistable) {
        Field[] fields =  persistable.getClass().getDeclaredFields();
        List<String> names = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            names.add(field.getName());
        }

        return String.join(", ", names);
    }

    public static String generateFieldsValues(Persistable persistable) {
        Field[] fields =  persistable.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();

        for(Field field : fields) {
            field.setAccessible(true);
            logger.debug("Field type: " + field.getType());
            try {
                Object object = field.get(persistable);
                logger.debug("Field value: " + object);
                logger.debug("Integer: " + field.getType().isAssignableFrom(Integer.class));
                if(field.getType().isAssignableFrom(String.class)) {
                    query.append("'")
                            .append(object)
                            .append("'")
                            .append(", ");
                } else if(field.getType().isAssignableFrom(Boolean.class)) {
                    query.append(String.valueOf(object).toUpperCase())
                            .append(", ");
                } else {
                    query.append(object)
                            .append(", ");
                }

            } catch (IllegalAccessException e) {
                logger.error("Field not found");
                logger.error(e.getMessage());
            }
        }

        logger.debug("Fields values: " + query);
        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }
}
