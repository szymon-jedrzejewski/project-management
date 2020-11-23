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

import java.util.Collections;
import java.util.List;

public class CreateDocumentAction implements Action {

    private DocumentView view;
    private DocumentRepository repository;

    public CreateDocumentAction(DocumentView view, DocumentRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public String getDisplayName() {
        return "Create Document";
    }

    @Override
    public void execute() {
        try {
            String title = view.getString("Enter title: ");
            String description = view.getString("Enter description: ");
            String topic = view.getString("Enter topic: ");
            String content = view.getString("Enter content: ");
            int project = Integer.parseInt(view.getString("Enter project: "));
            int creator = Integer.parseInt(view.getString("Enter creator: "));
            Document document = repository.create(new Document(title, description,topic, content, project, creator));

            view.info("Project successfully created. Project id is: ");
        } catch (RepositoryException e) {
            view.error("There was an error while adding project - project name already exists.");
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Collections.singletonList(Role.ADMINISTRATOR);
    }
}
