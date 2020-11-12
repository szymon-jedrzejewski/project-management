package pl.ttpsc.javaupdate.project.persistence.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.exception.PersistenceManagerException;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.utility.SqlQueryUtility;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
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
    public Persistable create(Persistable persistable) throws PersistenceManagerException {
        String query = SqlQueryUtility.createQueryOf(persistable);
        logger.debug("Generated query: " + query);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
            return null;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
        }
        throw new PersistenceManagerException();
    }

    @Override
    public void update(Persistable persistable) {

    }

    @Override
    public void delete(QuerySpec qs) {
        try {
            String query = SqlQueryUtility.generateDeleteQuery(qs);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) throws PersistenceManagerException {


        try {
            String query = SqlQueryUtility.createQueryOf(querySpec);
            logger.debug("Find query manager: " + query);
            List<Persistable> persistables = new ArrayList<>();
            Constructor<?> constructor = querySpec.getTableName().getDeclaredConstructor();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

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

            connection.close();
            logger.debug("Persistables: " + persistables.toString());
            logger.debug("Persistables size: " + persistables.size());
            return persistables;
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

            logger.error("SQLException: " + e.getMessage());
        }

        throw new PersistenceManagerException();
    }
}
