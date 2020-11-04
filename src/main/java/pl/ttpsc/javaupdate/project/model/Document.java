package pl.ttpsc.javaupdate.project.model;

public class Document {

    private String title;
    private String description;
    private User creator;
    private String topic;
    private String content;

    public Document() {
    }

    public Document(String title, String description, User creator, String topic, String content) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.topic = topic;
        this.content = content;
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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creator=" + creator +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
