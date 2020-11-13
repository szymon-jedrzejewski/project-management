package pl.ttpsc.javaupdate.project.persistence;

import pl.ttpsc.javaupdate.project.exception.PersistenceException;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable) throws PersistenceException;
	
	void update(Persistable persistable) throws PersistenceException;

	void delete(Class<?> clazz, int id) throws PersistenceException;

	List<Persistable> find(QuerySpec querySpec) throws PersistenceException;
      
}
