package pl.ttpsc.javaupdate.project.action.document;


import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Project;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.repository.ProjectRepository;
import pl.ttpsc.javaupdate.project.view.DocumentView;
import pl.ttpsc.javaupdate.project.view.ProjectView;

import java.util.Arrays;
import java.util.List;

public class ShowDocumentAction implements Action {

    private DocumentView view;
    private DocumentRepository repository;

    public ShowDocumentAction(DocumentView view, DocumentRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void execute() {
        String documentName = view.getString("Please enter project name: ");
        List<Document> documents = null;
        try {
            documents = repository.findByName(documentName);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        view.display(documents);
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ENGINEER, Role.MANAGER);
    }

    @Override
    public String getDisplayName() {
        return "Show Project";
    }

}
