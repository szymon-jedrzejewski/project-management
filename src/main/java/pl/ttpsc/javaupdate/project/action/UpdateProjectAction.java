package pl.ttpsc.javaupdate.project.action;

import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Arrays;
import java.util.List;

public class UpdateProjectAction implements Action {

    private ProjectView view;
    private ProjectRepository repository;

    public UpdateProjectAction(ProjectView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Update Project";
    }

    @Override
    public void execute() {
        try {
            int id = Integer.parseInt(view.getString("Enter id of document you want edit: "));
            Project project = repository.findById(id);
            String name = view.getString("Please enter new name: ");
            project.setName(name);
            String description = view.getString("Please enter new description: ");
            project.setDescription(description);
            repository.update(project);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.HR);
    }
}
