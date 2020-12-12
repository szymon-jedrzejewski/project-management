package pl.ttpsc.javaupdate.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.view.console.ConsoleMenu;

import java.lang.reflect.InvocationTargetException;

public class ConsoleApplication {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

            Config config = new Config()
                    .withSqlPersistence();

            ConsoleMenu menu = new ConsoleMenu();
            Action action = menu.choseAction(config.getActionList());
            action.execute();
            System.out.println("Application start failed.");

    }
}
