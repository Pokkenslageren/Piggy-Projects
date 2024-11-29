package ProjectPortal.Repository;

import ProjectPortal.Model.Project;
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

    public void createProject(int companyId, String projectName, String startDate, String endDate, String assignedEmployees, double totalEstimatedCost, int totalAvailableEmployees, boolean isComplete, String projectDescription){
        String query = "INSERT INTO projects(company_id, project_name, start_date, end_date, total_estimated_cost, total_available_employees, is_complete, project_description) VALUES (?,?,?,?,?,?,?,?,?);";
        jdbcTemplate.update(query, companyId, projectName, startDate, endDate, assignedEmployees, totalEstimatedCost, totalAvailableEmployees, isComplete, projectDescription);
    }

    public Project readProject(int projectId){
        String query = "SELECT * FROM projects WHERE projectid = ?;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.queryForObject(query,rowMapper,projectId);
    }

    public List<Project> readAllProjects(){
        String query = "SELECT * FROM projects;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Project.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void updateProject(int companyId, String projectName, String startDate, String endDate, String assignedEmployees, double totalEstimatedCost, int totalAvailableEmployees, boolean isComplete, String projectDescription){
        String query = "UPDATE projects SET company_id = ?, project_name = ?, start_date = ?, end_date = ?, total_estimated_cost = ?, total_available_employees = ?, is_complete = ?, project_description = ?;";
        jdbcTemplate.update(query, companyId, projectName, startDate, endDate, assignedEmployees, totalEstimatedCost, totalAvailableEmployees, isComplete, projectDescription);
    }

    public void deleteProject(int projectId){
        String query = "DELETE FROM projects WHERE projectid = ?;";
        jdbcTemplate.update(query,projectId);
    }


}
