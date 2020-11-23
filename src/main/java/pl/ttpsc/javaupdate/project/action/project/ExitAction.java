package pl.ttpsc.javaupdate.project.action.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.persistence.sql.SqlPersistenceManager;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ExitAction implements Action {

    private static final Logger logger = LogManager.getLogger(SqlPersistenceManager.class);
    private Connection connection;
    private ProjectView view;

    public ExitAction(Connection connection, ProjectView view) {
        this.connection = connection;
        this.view = view;
    }

    @Override
    public void execute() {
        try {
            connection.close();
            final int SUCCESSFUL_EXIT = 0;
            System.exit(SUCCESSFUL_EXIT);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            view.error("Error during closing application");
        }
    }

    @Override
    public String getDisplayName() {
        return "Exit";
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.ENGINEER, Role.MANAGER, Role.HR);
    }
}
