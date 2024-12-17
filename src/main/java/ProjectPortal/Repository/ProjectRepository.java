package ProjectPortal.Repository;

import java.time.LocalDate;
import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.Collections;
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

        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create project", e);
        }
    }

    public Project readProject(int projectId) {
        try {
            String query = "SELECT * FROM projects WHERE project_id = ?;";
            RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
            return jdbcTemplate.queryForObject(query, rowMapper, projectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Project> readAllProjects() {
        try {
            String query = "SELECT * FROM projects;";
            RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
            return jdbcTemplate.query(query, rowMapper);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public void updateProject(Project project, int projectId) {
        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update project", e);
        }
    }

    public void deleteProject(int projectId) {
        try {
            String query = "DELETE FROM projects WHERE project_id = ?;";
            jdbcTemplate.update(query, projectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot delete project", e);
        }
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

    public int calculateTotalProjectEmployees(int projectId) {
        String subprojectQuery = "SELECT subproject_id FROM subprojects WHERE project_id = ?";
        List<Integer> subprojectIds = jdbcTemplate.queryForList(subprojectQuery, Integer.class, projectId);

        int totalEmployees = 0;

        for (Integer subprojectId : subprojectIds) {
            String taskQuery = "SELECT SUM(assigned_employees) FROM tasks WHERE subproject_id = ?";
            Integer subprojectEmployees = jdbcTemplate.queryForObject(taskQuery, Integer.class, subprojectId);

            if (subprojectEmployees != null) {
                totalEmployees += subprojectEmployees;
            }
        }

        return totalEmployees;
    }

    public String formatForJavaScript(LocalDate date) {
        return String.format("new Date(%d, %d, %d)",
                date.getYear(),
                date.getMonthValue() - 1,
                date.getDayOfMonth());
    }
}