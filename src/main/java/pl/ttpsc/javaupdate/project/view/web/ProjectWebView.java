package pl.ttpsc.javaupdate.project.view.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectWebView implements ProjectView {
    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ProjectWebView(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void display(Project project) {
        request.setAttribute("project", project);
        try {
            request.getRequestDispatcher("/show_project.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Error during displaying project");
        }
    }

    @Override
    public void display(List<Project> projects) {
        request.setAttribute("projects", projects);
        try {
            request.getRequestDispatcher("/show_projects.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Error during displaying projects");
        }
    }

    @Override
    public String getString(String name) {
        return request.getParameter(name);
    }

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void error(String msg) {
        System.out.println(msg);
    }
}
