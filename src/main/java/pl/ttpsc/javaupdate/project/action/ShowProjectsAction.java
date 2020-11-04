package pl.ttpsc.javaupdate.project.action;


import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Arrays;
import java.util.List;

public class ShowProjectsAction implements Action {

	private ProjectView view;
	private ProjectRepository repository;

	public ShowProjectsAction(ProjectView view, ProjectRepository repository) {
		this.view = view;
		this.repository = repository;
	}

	@Override
	public void execute() {
		String projectName = view.getProjectName();
		List<Project> projects = repository.findByName(projectName);
		view.display(projects);
	}

	@Override
	public List<Role> getAllowedRoles() {
		return Arrays.asList(Role.ENGINEER, Role.MANAGER);
	}

	@Override
	public String getDisplayName() {
		return "Show Projects";
	}

}
