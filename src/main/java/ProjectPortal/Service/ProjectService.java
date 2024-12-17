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

    public void createProject(Project project) {
        projectRepository.createProject(project);
    }

    public Project readProject(int projectId) {
        return projectRepository.readProject(projectId);
    }

    public List<Project> readAllProjects() {
        List<Project> projects = projectRepository.readAllProjects();

        // Populate each project with calculatedn values
        for (Project project : projects) {
            updateProjectCalculations(project);
        }

        return projects;
    }

    public void updateProject(Project project, int projectId) {
        projectRepository.updateProject(project, projectId);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }

    public void updateProjectCalculations(Project project) {
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(project.getProjectId());

        double calculatedActualCost = calculateTotalActualCost(subprojects);
        project.setTotalActualCost(calculatedActualCost);

        int totalEmployees = calculateTotalProjectEmployees(project.getProjectId());
        project.setTotalAssignedEmployees(totalEmployees);
    }

    public double calculateTotalActualCost(List<Subproject> subprojects) {
        return projectRepository.calculateTotalActualCost(subprojects);
    }

    public int calculateTotalProjectEmployees(int projectId) {
        return projectRepository.calculateTotalProjectEmployees(projectId);
    }

    public String formatForJavaScript(LocalDate date){
        return projectRepository.formatForJavaScript(date);
    }
}
