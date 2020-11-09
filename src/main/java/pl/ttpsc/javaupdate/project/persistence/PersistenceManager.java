package pl.ttpsc.javaupdate.project.persistence;

import pl.ttpsc.javaupdate.project.exception.SqlPersistenceManagerException;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable) throws SqlPersistenceManagerException;
	
	void update(Persistable persistable) throws SqlPersistenceManagerException;

	void delete(Persistable persistable) throws SqlPersistenceManagerException;

	List<Persistable> find(QuerySpec querySpec) throws SqlPersistenceManagerException;
      
}
