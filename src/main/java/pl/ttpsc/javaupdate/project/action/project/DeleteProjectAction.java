package pl.ttpsc.javaupdate.project.action.project;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Collections;
import java.util.List;

public class DeleteProjectAction implements Action {

    private ProjectView view;
    private ProjectRepository repository;

    public DeleteProjectAction(ProjectView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Delete Project";
    }

    @Override
    public void execute() {
        int id = Integer.parseInt(view.getString("Enter project id u want delete: "));
        repository.delete(id);
        view.info("Project deleted successfully!");
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
