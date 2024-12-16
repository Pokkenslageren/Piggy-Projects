package ProjectPortal.Repository;

import java.time.LocalDate;
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
public class ProjectRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterator<Double> iterator() {
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
                project.getTotalAssignedEmployees(),
                project.isComplete(),
                project.getProjectDescription()
        );
    }

    public Project readProject(int projectId) {
        String query = "SELECT * FROM projects WHERE project_id = ?;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.queryForObject(query, rowMapper, projectId);
    }

    public List<Project> readAllProjects() {
        String query = "SELECT * FROM projects;";
        RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void updateProject(Project project, int projectId) {
        String query = "UPDATE projects SET company_id = ?, project_name = ?, start_date = ?, " +
                "end_date = ?, total_estimated_cost = ?, total_assigned_employees = ?, " +
                "is_complete = ?, project_description = ? WHERE project_id = ?;";
        jdbcTemplate.update(query,
                project.getCompanyId(),
                project.getProjectName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTotalEstimatedCost(),
                project.getTotalAssignedEmployees(),
                project.isComplete(),
                project.getProjectDescription(),
                projectId
        );
    }

    public void deleteProject(int projectId) {
        String query = "DELETE FROM projects WHERE project_id = ?;";
        jdbcTemplate.update(query, projectId);
    }

    public int calculateTotalAvailableEmployees(List<Subproject> listOfSubprojects, Project project) {
        int totalProjectEmployees = project.getTotalAssignedEmployees();
        int totalEmployeesInUse = 0;

        for (Subproject sub : listOfSubprojects) {
            totalEmployeesInUse += sub.getTotalAssignedEmployees();
        }
        return totalProjectEmployees - totalEmployeesInUse;
    }

    public double calculateTotalActualCost(List<Subproject> subprojects) {
        double totalActualCost = 0.0;

        for (Subproject subproject : subprojects) {
            String query = "SELECT SUM(estimated_cost) FROM tasks WHERE subproject_id = ?";
            Double subprojectCost = jdbcTemplate.queryForObject(query, Double.class, subproject.getSubprojectId());

            if (subprojectCost != null) {
                totalActualCost += subprojectCost;
            }
        }

        return totalActualCost;
    }

    public String formatForJavaScript(LocalDate date) {
        return String.format("new Date(%d, %d, %d)",
                date.getYear(),
                date.getMonthValue() - 1,
                date.getDayOfMonth());
    }
}