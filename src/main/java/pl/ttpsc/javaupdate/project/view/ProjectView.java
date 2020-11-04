package pl.ttpsc.javaupdate.project.view;

import pl.ttpsc.javaupdate.project.model.Project;

import java.util.List;

public interface ProjectView {
    void display(List<Project> project);
    void display(Project project);

    String getProjectName();
}
