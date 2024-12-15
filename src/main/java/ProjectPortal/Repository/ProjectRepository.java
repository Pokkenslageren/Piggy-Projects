package ProjectPortal.Repository;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.Iterator;
import java.util.List;

@Repository
public class ProjectRepository implements Iterable<Double>  {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterator<Double> iterator(){
        return null;
    }

    public void createProject(Project project) {
        String query = "INSERT INTO projects (company_id, user_id, project_name, start_date, " +
                "end_date, total_estimated_cost, total_assigned_employees, is_complete, " +
                "project_description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,
                project.getCompanyId(),
                project.getUserId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTotalEstimatedCost(),
                project.getAvailableEmployees(),
                false,
                project.getProjectDescription()
        );
    }

    public Project readProject(int projectId){
        String query = "SELECT * FROM projects WHERE project_id = ?;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.queryForObject(query,rowMapper,projectId);
    }

    public List<Project> readAllProjects(){
        String query = "SELECT * FROM projects;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Project.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void updateProject(Project project, int projectId){
        String query = "UPDATE projects SET company_id = ?, project_name = ?, start_date = ?, end_date = ?, total_estimated_cost = ?, total_assigned_employees = ?, is_complete = ?, project_description = ? WHERE project_id = ?;";
        jdbcTemplate.update(query, project.getCompanyId(), project.getProjectName(), project.getStartDate(), project.getEndDate(),  project.getTotalEstimatedCost(), project.getAssignedEmployees(), project.isComplete(), project.getProjectDescription());
    }

    public void deleteProject(int projectId){
        String query = "DELETE FROM projects WHERE project_id = ?;";
        jdbcTemplate.update(query,projectId);
    }

    /**
     * Calculates number of available employees based on assigned employees in subprojects
     * @param listOfSubprojects A list of all subprojects associated with the project
     * @param project The given project
     * @return The number of employees not assigned to a subproject or task
     */
    public int calculateTotalAvailableEmployees(List<Subproject> listOfSubprojects, Project project){
        var iterator = listOfSubprojects.iterator();
        int totalProjectEmployees = project.getAssignedEmployees();
        int totalEmployeesInUse = 0;
        while(iterator.hasNext()){
            totalEmployeesInUse = totalProjectEmployees + iterator.next().getTotalAssignedEmployees();
        }
        return totalProjectEmployees - totalEmployeesInUse;
    }

    /**
     * Calculates the actual total project cost based on the sum of all subproject costs
     * @return the actual total cost of the project
     */
    public double calculateTotalActualCost(List<Subproject> listOfSubprojects){
        var iterator = listOfSubprojects.iterator();
        double totalActualCost = 0.0;
        while(iterator.hasNext()){
            totalActualCost = totalActualCost + iterator.next().getTotalActualCost();
        }
        return totalActualCost;
    }



}
