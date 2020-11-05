package pl.ttpsc.javaupdate.project.persistence.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.lang.reflect.Field;
import java.util.List;

public class SqlPersistenceManager implements PersistenceManager {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    @Override
    public Persistable create(Persistable persistable) {
        String query = "INSERT INTO "
                + persistable.getClass().getSimpleName().toLowerCase() + "s "
                + "(" + getFieldsNames(persistable) + ") "
                + "VALUES (" + getFieldsValues(persistable) + ");";

        logger.info("Generated query: " + query);
        return null;
    }

    @Override
    public void update(Persistable persistable) {

    }

    @Override
    public void delete(Persistable persistable) {

    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) {
        return null;
    }

    private String getFieldsNames(Persistable persistable) {
        Field[] fields =  persistable.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            query.append(field.getName());
            query.append(", ");
            logger.info("Actual query: " + query);
        }

        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }

    private String getFieldsValues(Persistable persistable) {
        Field[] fields =  persistable.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder();

        for(Field field : fields) {
            field.setAccessible(true);
            logger.info("Field type: " + field.getType());
            try {
                Object object = field.get(persistable);
                logger.info("Field value: " + object);
                logger.info("Integer: " + field.getType().isAssignableFrom(Integer.class));
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

        logger.info("Fields values: " + query);
        return query.deleteCharAt(query.lastIndexOf(",")).toString().trim();
    }
}
