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

        // Populate each project with calculatedn values
        for (Project project : projects) {
            updateProjectCalculations(project);
        }

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

    /**
     * Updates project calculations for the given project. This includes calculating
     * the total actual cost based on associated subprojects and determining the total number
     * of employees assigned to the project.
     * @param project the project whose calculations are to be updated. It must include a valid project ID for data retrieval and updates.
     */
    public void updateProjectCalculations(Project project) {
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(project.getProjectId());

        double calculatedActualCost = calculateTotalActualCost(subprojects);
        project.setTotalActualCost(calculatedActualCost);

        int totalEmployees = calculateTotalProjectEmployees(project.getProjectId());
        project.setTotalAssignedEmployees(totalEmployees);
    }

    /**
     * Calculates the total actual cost for the provided list of subprojects.
     * @param subprojects the list of subprojects for which the total actual cost is to be calculated
     * @return the calculated total actual cost as a double value
     */
    public double calculateTotalActualCost(List<Subproject> subprojects) {
        return projectRepository.calculateTotalActualCost(subprojects);
    }

    /**
     * Calculates the total number of employees assigned to all tasks
     * within a specific project.
     * @param projectId The unique identifier of the project for which
     *                  the total number of assigned employees is to be calculated.
     * @return The total number of employees assigned to the specified project.
     */
    public int calculateTotalProjectEmployees(int projectId) {
        return projectRepository.calculateTotalProjectEmployees(projectId);
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
