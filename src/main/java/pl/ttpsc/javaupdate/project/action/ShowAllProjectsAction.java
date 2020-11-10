package pl.ttpsc.javaupdate.project.action;

import pl.ttpsc.javaupdate.project.exception.PersistenceManagerException;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Collections;
import java.util.List;

public class ShowAllProjectsAction implements Action {

    private ProjectView view;
    private ProjectRepository repository;

    public ShowAllProjectsAction(ProjectView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Show All Projects";
    }

    @Override
    public void execute() {
        try {
            view.display(repository.findAll());
        } catch (PersistenceManagerException e) {
            view.error("Cannot find projects");
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
