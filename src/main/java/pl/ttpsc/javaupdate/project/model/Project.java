package pl.ttpsc.javaupdate.project.model;

import pl.ttpsc.javaupdate.project.persistence.Persistable;

import java.util.List;

public class Project implements Persistable {
    private String name;
    private String description;
    private String creator;
    private List<Document> documents;
    private List<User> assignedUsers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                ", documents=" + documents +
                ", assignedUsers=" + assignedUsers +
                '}';
    }
}
