package pl.ttpsc.javaupdate.project.view;

import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Project;

import java.util.List;

public interface DocumentView {
    void display(List<Document> documents);

    void display(Document document);

    String getString(String message);

    void info(String msg);

    void error(String msg);
}
