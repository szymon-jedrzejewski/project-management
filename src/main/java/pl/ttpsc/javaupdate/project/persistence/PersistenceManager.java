package pl.ttpsc.javaupdate.project.persistence;

import java.util.List;

public interface PersistenceManager {
	
	Persistable create(Persistable persistable);
	
	void update(Persistable persistable);

	void delete(Persistable persistable);

	List<Persistable> find(QuerySpec querySpec);
      
}
