package pl.ttpsc.javaupdate.project.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.exception.PersistenceException;
import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

public class DocumentRepository {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private final PersistenceManager persistence;

    public DocumentRepository(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    public Document create (Document document) throws RepositoryException {
        try {
            return (Document)persistence.create(document);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new RepositoryException();
    }
}
