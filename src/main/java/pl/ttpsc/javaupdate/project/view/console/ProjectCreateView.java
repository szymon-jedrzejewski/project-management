package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.List;
import java.util.Scanner;

public class ProjectCreateView implements ProjectView {

    public int getId() {
        System.out.print("Enter project id: ");
        return new Scanner(System.in).nextInt();
    }

    public String getName() {
        System.out.print("Enter project name: ");
        return new Scanner(System.in).next();
    }

    public String getDescription() {
        System.out.print("Enter project description: ");
        return new Scanner(System.in).next();
    }

    public int getCreator() {
        System.out.print("Enter project creator: ");
        return new Scanner(System.in).nextInt();
    }

    @Override
    public void display(List<Project> projects) {

    }

    @Override
    public void display(Project project) {
        System.out.println(project.toString());
    }

    @Override
    public String getProjectName() {
        return null;
    }
}
