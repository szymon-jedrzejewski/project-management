package pl.ttpsc.javaupdate.project.model;

import pl.ttpsc.javaupdate.project.persistence.Persistable;

public class Document implements Persistable {

    private int id;
    private String title;
    private String description;
    private String topic;
    private String content;
    private int project;
    private int creator;

    public Document() {
    }

    public Document(String title, String description, String topic, String content, int project, int creator) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.content = content;
        this.project = project;
        this.creator = creator;
    }

    public Document(int id, String title, String description, String topic, String content, int project, int creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.content = content;
        this.project = project;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", project=" + project +
                ", creator=" + creator +
                '}';
    }
}
