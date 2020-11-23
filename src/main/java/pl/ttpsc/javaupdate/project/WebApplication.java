package pl.ttpsc.javaupdate.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.action.project.ShowAllProjectsAction;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.web.ProjectWebView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index.html")
public class WebApplication extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = null;

        String url = "jdbc:postgresql://localhost:5432/project_management";

        String login = "postgres";
        String password = "postgres";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        String actionName = request.getParameter("action");
        List<Action> actions = new ArrayList<>();
        Action action = new ShowAllProjectsAction(new ProjectWebView(request, response),
                new ProjectRepository(new SqlPersistenceManager(connection)));
                action.execute();
        System.out.println(actionName);
    }
}