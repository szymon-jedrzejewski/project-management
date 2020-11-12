package pl.ttpsc.javaupdate.project.model;

import pl.ttpsc.javaupdate.project.persistence.Persistable;

import java.util.Arrays;

public class User implements Persistable {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private Character[] password;
    private boolean isAdmin;

    public User() {
    }

    public User(String firstName, String lastName, String username, Character[] password, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(int id, String firstName, String lastName, String username, Character[] password, boolean isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Character[] getPassword() {
        return password;
    }

    public void setPassword(Character[] password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + Arrays.toString(password) + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
