package pl.ttpsc.javaupdate.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.action.*;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.console.ConsoleMenu;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Config config = new Config()
                .withSqlPersistence();

        ConsoleMenu menu = new ConsoleMenu();

        Connection connection;
        String url = "jdbc:postgresql://localhost:5432/project_management";

        String login = "postgres";
        String password = "postgres";


        try {
            connection = DriverManager.getConnection(url, login, password);
            List<Action> actions = new ArrayList<>();
            actions.add(new ShowAllProjectsAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ShowProjectsAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new CreateProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new DeleteProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ExitAction(connection, new ProjectConsoleView()));
            Action action = menu.choseAction(actions);
            action.execute();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            System.out.println("Application start failed.");
        }
    }
}
