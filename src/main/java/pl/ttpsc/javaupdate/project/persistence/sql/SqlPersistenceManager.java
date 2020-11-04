package pl.ttpsc.javaupdate.project.persistence.sql;

import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;

import java.util.List;

public class SqlPersistenceManager implements PersistenceManager {

    @Override
    public Persistable create(Persistable persistable) {
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
}
