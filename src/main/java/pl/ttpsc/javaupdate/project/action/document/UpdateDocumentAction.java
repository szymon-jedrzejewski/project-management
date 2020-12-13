package pl.ttpsc.javaupdate.project.action.document;

import pl.ttpsc.javaupdate.project.action.Action;
import pl.ttpsc.javaupdate.project.exception.RepositoryException;
import pl.ttpsc.javaupdate.project.model.Document;
import pl.ttpsc.javaupdate.project.model.Role;
import pl.ttpsc.javaupdate.project.repository.DocumentRepository;
import pl.ttpsc.javaupdate.project.view.DocumentView;

import java.util.Arrays;
import java.util.List;

public class UpdateDocumentAction implements Action {

    private DocumentView view;
    private DocumentRepository repository;

    public UpdateDocumentAction(DocumentView view, DocumentRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Update Document";
    }

    @Override
    public void execute() {
        try {
            int id = view.getInt("Enter id of document you want edit: ");
            Document document = repository.findById(id);
            String title = view.getString("Please enter new title: ");
            String description = view.getString("Please enter new description: ");
            String topic = view.getString("Please enter new topic: ");
            String content = view.getString("Please enter new content: ");
            int project = view.getInt("Please enter new project: ");

            document.setTitle(title);
            document.setDescription(description);
            document.setTopic(topic);
            document.setContent(content);
            document.setProject(project);

            repository.update(document);
            view.info("Document updated successfully!");
        } catch (RepositoryException e) {
            view.error("Document updated successfully!");
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.HR);
    }
}
