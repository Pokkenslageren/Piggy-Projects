package ProjectPlanner.Repository;

import ProjectPlanner.Model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    public void createProject(){

    }

    public Project readProject(int projectId){
        String query = "SELECT * FROM projects WHERE projectid = ?;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.queryForObject(query,rowMapper,projectId);
    }

    public List<Project> readAllProjects(){
        String query = "SELECT * FROM projets;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Project.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void updateProject(int projectId){

    }

    public void deleteProject(int projectId){
        String query = "DELETE FROM projects WHERE projectid = ?;";
        jdbcTemplate.update(query,projectId);
    }


}
