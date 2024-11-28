package ProjectPlanner.Service;

import ProjectPlanner.Model.Project;
import ProjectPlanner.Repository.ProjectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository = new ProjectRepository(new JdbcTemplate()); //maybe?

    public void createProject(){
        projectRepository.createProject();
    }

    public Project readProject(int projectId){
        return projectRepository.readProject(projectId);
    }

    public List<Project> readAllProjects(){
        return projectRepository.readAllProjects();
    }

    public void updateProject(int projectId){
        projectRepository.updateProject(projectId);
    }

    public void delteProject(int projectId){
        projectRepository.deleteProject(projectId);
    }


}
