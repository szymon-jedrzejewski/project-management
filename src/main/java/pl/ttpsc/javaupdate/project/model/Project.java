package pl.ttpsc.javaupdate.project.model;

import pl.ttpsc.javaupdate.project.persistence.Persistable;

public class Project implements Persistable {
    private int id;
    private String name;
    private String description;
    private int creator;

    public Project() {
    }

    public Project(int id,String name, String description, int creator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

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

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }


    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
