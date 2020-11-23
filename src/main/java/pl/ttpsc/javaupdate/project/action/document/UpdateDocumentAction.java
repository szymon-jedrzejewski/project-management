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
            int id = Integer.parseInt(view.getString("Enter id of document you want edit: "));
            Document document = repository.findById(id);

            String title = view.getString("Please enter new title: ");
            document.setTitle(title);

            String description = view.getString("Please enter new description: ");
            document.setDescription(description);

            String topic = view.getString("Please enter new topic: ");
            document.setTopic(topic);

            String content = view.getString("Please enter new content: ");
            document.setContent(content);

            int project = Integer.parseInt(view.getString("Please enter new project: "));
            document.setProject(project);

            int creator = Integer.parseInt(view.getString("Please enter new project: "));
            document.setCreator(creator);
            repository.update(document);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAllowedRoles() {
        return Arrays.asList(Role.ADMINISTRATOR, Role.HR);
    }
}
