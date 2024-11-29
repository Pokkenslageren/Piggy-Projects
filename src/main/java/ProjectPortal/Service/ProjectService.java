package ProjectPortal.Service;

import ProjectPortal.Model.Project;
import ProjectPortal.Repository.ProjectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository = new ProjectRepository(new JdbcTemplate()); //maybe?

    public void createProject(int companyId, String projectName, String startDate, String endDate, String assignedEmployees, double totalEstimatedCost, int totalAvailableEmployees, boolean isComplete, String projectDescription){
        projectRepository.createProject(companyId, projectName, startDate, endDate, assignedEmployees, totalEstimatedCost, totalAvailableEmployees, isComplete,projectDescription);
    }

    public Project readProject(int projectId){
        return projectRepository.readProject(projectId);
    }

    public List<Project> readAllProjects(){
        return projectRepository.readAllProjects();
    }

    public void updateProject(int companyId, String projectName, String startDate, String endDate, String assignedEmployees, double totalEstimatedCost, int totalAvailableEmployees, boolean isComplete, String projectDescription){
        projectRepository.updateProject(companyId, projectName, startDate, endDate, assignedEmployees, totalEstimatedCost, totalAvailableEmployees, isComplete,projectDescription);
    }

    public void deleteProject(int projectId){
        projectRepository.deleteProject(projectId);
    }


}
