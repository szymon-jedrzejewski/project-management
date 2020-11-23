package pl.ttpsc.javaupdate.project.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Config {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private PersistenceManager persistenceManager;
    private List<Action> actions;
    private JSONParser parser;
    private JSONObject object;

    public Config() {
        parser = new JSONParser();
        try {
            object  = (JSONObject)parser.parse(new FileReader("src/main/resources/databaseCredentials.json"));
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public Config withSqlPersistence() {
        setPersistenceManager(new SqlPersistenceManager());
        return this;
    }

    public String getUlr() {
        return (String)object.get("url");
    }

    public String getLogin() {
        return (String)object.get("login");
    }

    public String getPassword() {
        return (String)object.get("password");
    }

    public Config withXmlPersistence() {
        // TODO
        return this;
    }
}
