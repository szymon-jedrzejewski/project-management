package pl.ttpsc.javaupdate.project.action.document;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.view.DocumentView;

import java.util.Collections;
import java.util.List;

public class DeleteDocumentAction implements Action {

    private DocumentView view;
    private DocumentRepository repository;

    public DeleteDocumentAction(DocumentView view, DocumentRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Delete Document";
    }

    @Override
    public void execute() {
        int id = view.getInt("Enter project id you want delete: ");
        repository.delete(id);
        view.info("Document deleted successfully!");
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
