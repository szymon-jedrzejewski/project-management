package pl.ttpsc.javaupdate.project.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.SearchCondition;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private final PersistenceManager persistence;

    public ProjectRepository(PersistenceManager persistence) {
        this.persistence = persistence;
    }

    public Project create(Project project) {
        return (Project)persistence.create(project);
    }

    public List<Project> findByName(String name) {
        QuerySpec qs = new QuerySpec();
        qs.setTableName(Project.class);
        //TODO replace string operator with enum
        qs.appendWhere(new SearchCondition("name", "=", "'" + name + "'"));
        logger.debug("Append query: " + qs.getQuery());
        List<Persistable> persistables = persistence.find(qs);
        logger.debug("Project list: " + persistables.toString());

        List<Project> projects = new ArrayList<>();
        persistables.forEach(p -> projects.add((Project)p));
        return projects;
    }
}
