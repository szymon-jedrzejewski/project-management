package pl.ttpsc.javaupdate.project.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.exception.PersistenceException;
import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.persistence.*;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentRepository {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private final PersistenceManager persistence;

    public DocumentRepository(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    public Document create(Document document) throws RepositoryException {
        try {
            return (Document) persistence.create(document);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new RepositoryException();
    }

    public List<Document> findAll() throws RepositoryException {
        try {
            return persistence.find(new QuerySpec(Document.class))
                    .stream()
                    .map(persistable -> (Document) persistable)
                    .collect(Collectors.toList());
        } catch (PersistenceException e) {
            logger.error("PersistenceException: " + e.getMessage());
        }
        throw new RepositoryException();
    }

    public void update(Document document) {
        try {
            persistence.update(document);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            persistence.delete(Document.class, id);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
    }

    public List<Document> findByTitle(String title) throws RepositoryException{
        try {
            QuerySpec qs = new QuerySpec();
            qs.setTableName(Document.class);
            qs.append(QueryOperator.WHERE, new SearchCondition("title", Operator.EQUAL_TO, title));
            return persistence.find(qs)
                    .stream()
                    .map(persistable -> (Document) persistable)
                    .collect(Collectors.toList());
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new RepositoryException();
    }

    public Document findById(int id) throws RepositoryException {
        try {
            QuerySpec qs = new QuerySpec();
            qs.setTableName(Document.class);
            qs.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));
            final int FIRST_ELEMENT = 0;
            return (Document)persistence.find(qs).get(FIRST_ELEMENT);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new RepositoryException();
    }
}
