package pl.ttpsc.javaupdate.project.action;


import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Arrays;
import java.util.List;

public class ShowProjectAction implements Action {

    private ProjectView view;
    private ProjectRepository repository;

    public ShowProjectAction(ProjectView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void execute() {
        String projectName = view.getString("Please enter project name: ");
        List<Project> projects = null;
        try {
            projects = repository.findByName(projectName);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        view.display(projects);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER);
    }

    @Override
    public String getDisplayName() {
        return "Show Project";
    }

}
