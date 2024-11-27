package ProjectPlanner.Repository;

import ProjectPlanner.Model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void CreateProject(Project project){

    }

    public Project readProject(String projectName){
        return null;
    }

    public void updateProject(String projectName){

    }

    public void deleteProject(String projectName){

    }


}
