package pl.ttpsc.javaupdate.project.persistence.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.utility.SqlQueryUtility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlPersistenceManager implements PersistenceManager {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private Connection connection;

    public SqlPersistenceManager() {
    }

    public SqlPersistenceManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Persistable create(Persistable persistable) {
        String query = SqlQueryUtility.generateInsertQuery(persistable);
        logger.debug("Generated query: " + query);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
        }

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
//        String query = SqlQueryUtility.generateFindQuery(querySpec);
        String query = "SELECT * FROM projects";
        List<Persistable> persistables = new ArrayList<>();

        ResultSet result = null;
        try {
            Constructor<?> constructor = querySpec.getTableName().getDeclaredConstructor();
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                Persistable persistable = (Persistable)constructor.newInstance();
                logger.debug("Created object: " + persistable);
                Field[] fields = persistable.getClass().getDeclaredFields();

                for (Field field : fields) {
                    field.setAccessible(true);
                    logger.debug("Column name: " + field.getName());
                    logger.debug("Value: " + result.getObject(field.getName()));
                    field.set(persistable, result.getObject(field.getName()));
                }

                persistables.add(persistable);
                logger.debug("Persistable: " + persistable.toString());

            }
            connection.close();
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("SQLException: " + e.getMessage());
        }
        logger.debug("Persistables: " + persistables.toString());
        logger.debug("Persistables size: " + persistables.size());
        return persistables;
    }
}
