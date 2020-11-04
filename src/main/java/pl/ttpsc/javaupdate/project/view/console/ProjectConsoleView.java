package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.List;

public class ProjectConsoleView implements ProjectView {

    @Override
    public void display(Project project) {

    }

    @Override
    public void display(List<Project> project) {

    }

    @Override
    public String getProjectName() {
        return "My Project";
    }
}
