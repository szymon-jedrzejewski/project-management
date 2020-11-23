package pl.ttpsc.javaupdate.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.ttpsc.javaupdate.project.action.*;
import pl.ttpsc.javaupdate.project.action.project.*;
import pl.ttpsc.javaupdate.project.config.Config;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.console.ConsoleMenu;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;

import javax.json.stream.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsoleApplication {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {

        try {
            Config config = new Config()
                    .withSqlPersistence();
            Connection connection = DriverManager.getConnection(config.getUlr(), config.getLogin(), config.getPassword());

            List<Action> actions = new ArrayList<>();

            actions.add(new ShowAllProjectsAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ShowProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new CreateProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new UpdateProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new DeleteProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ExitAction(connection, new ProjectConsoleView()));

            ConsoleMenu menu = new ConsoleMenu();
            Action action = menu.choseAction(actions);
            action.execute();
        } catch (SQLException e) {
            logger.error("Exception: " + e.getMessage());
            System.out.println("Application start failed.");
        }
    }
}
