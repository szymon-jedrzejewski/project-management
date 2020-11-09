package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.List;

public class ProjectConsoleView implements ProjectView {

    private String projectName;

    public ProjectConsoleView() {
    }

    public ProjectConsoleView(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public void display(Project project) {
        System.out.println(project.toString());
    }

    @Override
    public void display(List<Project> projects) {
        for (Project project : projects) {
            System.out.println("Id: " + project.getId());
            System.out.println("Name: " + project.getName());
            System.out.println("Description: " + project.getDescription());
            System.out.println("Creator: " + project.getCreator());
        }
    }

    @Override
    public String getProjectName() {
        return projectName;
    }
}
