package pl.ttpsc.javaupdate.project.repository;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private PersistenceManager persistence;

    public List<Project> findByName(String name) {
        // TODO

        return new ArrayList<>();
    }
}
