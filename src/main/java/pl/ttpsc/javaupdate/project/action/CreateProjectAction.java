package pl.ttpsc.javaupdate.project.action;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.console.ProjectCreateView;

import java.util.Collections;
import java.util.List;

public class CreateProjectAction implements Action{

    private ProjectCreateView view;
    private ProjectRepository repository;

    public CreateProjectAction(ProjectCreateView view, ProjectRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Create Project";
    }

    @Override
    public void execute() {
        Project project = new Project(view.getId(), view.getName(), view.getDescription(), view.getCreator());
        repository.create(project);
        view.display(project);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
