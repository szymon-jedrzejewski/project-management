package pl.ttpsc.javaupdate.project.view;

import pl.ttpsc.javaupdate.project.model.Document;

import java.util.List;

public interface DocumentView extends View{
    void display(List<Document> documents);

    void display(Document document);
}
