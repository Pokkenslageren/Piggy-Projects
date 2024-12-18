package ProjectPortal.Service;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SubprojectService subprojectService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, SubprojectService subprojectService) {
        this.projectRepository = projectRepository;
        this.subprojectService = subprojectService;
    }

    /**
     * Creates a new project by passing the project object to the repository layer.
     * @param project the project instance containing the details of the project to be created
     */
    public void createProject(Project project) {
        projectRepository.createProject(project);
    }

    /**
     * Retrieves a project by its unique identifier.
     * @param projectId the unique identifier of the project to retrieve
     * @return the project associated with the given identifier, or null if no project is found
     */
    public Project readProject(int projectId) {
        return projectRepository.readProject(projectId);
    }

    /**
     * Retrieves all projects from the repository and updates each project
     * with calculated values such as total actual cost and total assigned employees.
     * @return a list of all projects with updated calculations.
     */
    public List<Project> readAllProjects() {
        List<Project> projects = projectRepository.readAllProjects();

        return projects;
    }

    /**
     * Updates an existing project with new details.
     * @param project   the updated project details
     * @param projectId the unique identifier of the project to be updated
     */
    public void updateProject(Project project, int projectId) {
        projectRepository.updateProject(project, projectId);
    }

    /**
     * Deletes a project based on the provided project ID.
     * @param projectId the unique identifier of the project to be deleted
     */
    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }


    public void markComplete(int projectId) {
        projectRepository.markComplete(projectId);
    }


    /**
     * Formats a given LocalDate into a JavaScript-compatible date string representation.
     * The method constructs the string in the format: "new Date(year, month, day)".
     * Note that the month is adjusted by subtracting 1 to match JavaScript's zero-based month indexing.
     * @param date the LocalDate instance to be formatted.
     * @return a formatted string representing the date in JavaScript's Date format.
     */
    public String formatForJavaScript(LocalDate date){
        return projectRepository.formatForJavaScript(date);
    }
}
