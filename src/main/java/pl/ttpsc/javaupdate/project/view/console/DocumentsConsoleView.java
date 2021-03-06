package pl.ttpsc.javaupdate.project.view.console;

import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.view.DocumentView;

import java.util.List;

public class DocumentsConsoleView implements DocumentView {

    @Override
    public void display(Document document) {
        System.out.println(document.toString());
    }

    @Override
    public void display(List<Document> documents) {
        for (Document document : documents) {
            System.out.println("Id: " + document.getId());
            System.out.println("Title: " + document.getTitle());
            System.out.println("Description: " + document.getDescription());
            System.out.println("Content: " + document.getContent());
            System.out.println("Project: " + document.getProject());
            System.out.println("Creator: " + document.getCreator());
            System.out.println();
        }
    }
}
