package pl.ttpsc.javaupdate.project.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.exception.PersistenceException;
import pl.ttpsc.javaupdate.project.exception.ProjectRepositoryException;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.*;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectRepository {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private final PersistenceManager persistence;

    public ProjectRepository(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    public Project create(Project project) throws ProjectRepositoryException {
        try {
            return (Project) persistence.create(project);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new ProjectRepositoryException();
    }

    public void delete(int id) {
        try {
            Project project = findById(id);
            QuerySpec qs = new QuerySpec(Project.class);
            qs.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));
            persistence.delete(Project.class, project.getId());
        } catch (PersistenceException | ProjectRepositoryException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
    }

    public Project findById(int id) throws ProjectRepositoryException{
        final int FIRST_PROJECT = 0;
        try {
            QuerySpec qs = new QuerySpec(Project.class);
            qs.append(QueryOperator.WHERE, new SearchCondition("id", Operator.EQUAL_TO, id));
            return (Project)persistence.find(qs).get(FIRST_PROJECT);
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new ProjectRepositoryException();
    }

    public List<Project> findByName(String name) throws ProjectRepositoryException {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Project.class);

        qs.append(QueryOperator.WHERE, new SearchCondition("name", Operator.EQUAL_TO, name));
        logger.debug("Append query: " + qs.getSpecs());

        try {
            return persistence
                    .find(qs)
                    .stream()
                    .map(persistable -> (Project) persistable)
                    .collect(Collectors.toList());

        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new ProjectRepositoryException();
    }

    public List<Project> findAll() throws PersistenceException {
        QuerySpec qs = new QuerySpec(Project.class);
        try {
            return persistence
                    .find(qs)
                    .stream()
                    .map(persistable -> (Project) persistable)
                    .collect(Collectors.toList());
        } catch (PersistenceException e) {
            logger.error("PersistenceManagerException: " + e.getMessage());
        }
        throw new PersistenceException();
    }
}
