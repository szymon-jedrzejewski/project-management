package pl.ttpsc.javaupdate.project.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.action.project.*;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.utility.JsonUtility;
import pl.ttpsc.javaupdate.project.view.console.ProjectConsoleView;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private PersistenceManager persistenceManager;
    private JSONParser parser;
    private JSONObject json;

    public Config() {
        parser = new JSONParser();
        try {
            json = (JSONObject) parser.parse(new FileReader("src/main/resources/databaseCredentials.json"));
        } catch (IOException | ParseException e) {
            logger.error("Exception: " + e.getMessage());
        }
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public Config withSqlPersistence() {
        setPersistenceManager(new SqlPersistenceManager());
        return this;
    }

    public List<Action> getActionList() {
        List<Action> actions = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(getUlr(), getLogin(), getPassword());
            actions.add(new ShowAllProjectsAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ShowProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new CreateProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new UpdateProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new DeleteProjectAction(new ProjectConsoleView(), new ProjectRepository(new SqlPersistenceManager(connection))));
            actions.add(new ExitAction(connection, new ProjectConsoleView()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return actions;
    }

    public String getUlr() {
        return JsonUtility.jsonToString(json, "url");
    }

    public String getLogin() {
        return JsonUtility.jsonToString(json, "login");
    }

    public String getPassword() {
        return JsonUtility.jsonToString(json, "password");
    }

    public Config withXmlPersistence() {
        // TODO
        return this;
    }
}
