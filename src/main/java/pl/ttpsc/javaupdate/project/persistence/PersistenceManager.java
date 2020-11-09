package pl.ttpsc.javaupdate.project.persistence;

import pl.ttpsc.javaupdate.project.exception.PersistenceManagerException;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable) throws PersistenceManagerException;
	
	void update(Persistable persistable) throws PersistenceManagerException;

	void delete(Persistable persistable) throws PersistenceManagerException;

	List<Persistable> find(QuerySpec querySpec) throws PersistenceManagerException;
      
}
