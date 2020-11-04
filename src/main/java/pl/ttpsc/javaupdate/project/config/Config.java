package pl.ttpsc.javaupdate.project.config;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.util.List;

public class Config {
    private PersistenceManager persistenceManager;
    private List<Action> actions;

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

    public Config withXmlPersistence() {
        // TODO
        return this;
    }
}
