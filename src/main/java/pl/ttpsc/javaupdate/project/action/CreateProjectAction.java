package pl.ttpsc.javaupdate.project.action;

import pl.ttpsc.javaupdate.project.exception.ProjectRepositoryException;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Collections;
import java.util.List;

public class CreateProjectAction implements Action {

    private ProjectView view;
    private ProjectRepository repository;

    public CreateProjectAction(ProjectView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Create Project";
    }

    @Override
    public void execute() {
        try {
            String name = view.getString("Enter name: ");
            String description = view.getString("Enter description: ");
            int creator = Integer.parseInt(view.getString("Enter creator: "));
            Project project = repository.create(new Project(name, description, creator));

            view.info("Project successfully created. Project id is: ");
        } catch (ProjectRepositoryException e) {
            view.error("There was an error while adding project - project name already exists.");
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
