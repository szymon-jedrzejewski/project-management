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
        String query = SqlQueryUtility.generateFindQuery(querySpec);
        List<Persistable> persistables = new ArrayList<>();

        ResultSet result = null;
        try {
            Constructor<?> constructor = querySpec.getTableName().getDeclaredConstructors()[1];
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);

            logger.debug("res: " + result.next());
            while (result.next()) {

                Field[] fields = query.getClass().getDeclaredFields();

                for (Field field : fields) {
                    result.getObject(field.getName());
                }
                Persistable persistable = (Persistable)constructor.newInstance();

                logger.debug("Result: " + result.getArray(1));

            }
            connection.close();
        } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("SQLException: " + e.getMessage());
        }
        return persistables;
    }
}
