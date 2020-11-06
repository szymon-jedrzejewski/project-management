package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.List;

public class ProjectConsoleView implements ProjectView {

    @Override
    public void display(Project project) {
        System.out.println(project.toString());
    }

    @Override
    public void display(List<Project> projects) {
        for (Project project : projects) {
            System.out.println(project.toString());
            System.out.println();
        }
    }

    @Override
    public String getProjectName() {
        return "My Project";
    }
}
