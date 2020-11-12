package pl.ttpsc.javaupdate.project.persistence;

import pl.ttpsc.javaupdate.project.exception.PersistenceException;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable) throws PersistenceException;
	
	void update(Persistable persistable) throws PersistenceException;

	void delete(QuerySpec querySpec) throws PersistenceException;

	List<Persistable> find(QuerySpec querySpec) throws PersistenceException;
      
}
