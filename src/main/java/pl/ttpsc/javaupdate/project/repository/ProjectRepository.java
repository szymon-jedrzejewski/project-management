package pl.ttpsc.javaupdate.project.repository;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.Persistable;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;
import pl.ttpsc.javaupdate.project.persistence.QuerySpec;
import pl.ttpsc.javaupdate.project.persistence.SearchCondition;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
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
        qs.addCondition(new SearchCondition("name", "=", name));
        List<Persistable> persistables = persistence.find(qs);
        List<Project> projects = new ArrayList<>();
        persistables.forEach(p -> projects.add((Project)p));
        return projects;
    }
}
