package pl.ttpsc.javaupdate.project.action;

import pl.ttpsc.javaupdate.project.model.Role;

import java.util.List;

public interface Action {
    String getDisplayName();

    void execute();

    List<Role> getAllowedRoles();
}
