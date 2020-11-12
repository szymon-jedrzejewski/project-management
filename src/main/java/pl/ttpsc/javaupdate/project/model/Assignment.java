package pl.ttpsc.javaupdate.project.model;

public class Assignment {

    private int id;
    private int userId;
    private int projectId;
    private Role role;

    public Assignment() {
    }

    public Assignment(int userId, int projectId, Role role) {
        this.userId = userId;
        this.projectId = projectId;
        this.role = role;
    }

    public Assignment(int id, int userId, int projectId, Role role) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Policies{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", role=" + role +
                '}';
    }
}
