package pl.ttpsc.javaupdate.project.model;

public class User {
    private String firstname;
    private String lastName;
    private String username;
    private String password;
    private Role systemRole;

    public User() {
    }

    public User(String firstname, String lastName, String username, String password, Role systemRole) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.systemRole = systemRole;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(Role systemRole) {
        this.systemRole = systemRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", systemRole=" + systemRole +
                '}';
    }
}
