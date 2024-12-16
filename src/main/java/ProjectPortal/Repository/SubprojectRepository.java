package ProjectPortal.Repository;


import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.*;

@Repository
public class SubprojectRepository {
    private final JdbcTemplate jdbcTemplate;

    public SubprojectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createSubproject(Subproject subproject) {
        String query = "INSERT INTO subprojects (project_id, subproject_name, start_date, " +
                "end_date, total_estimated_cost, total_actual_cost, " +
                "is_complete, subproject_description, hours_allocated, priority) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,
                subproject.getProjectId(),
                subproject.getSubprojectName(),
                subproject.getStartDate(),
                subproject.getEndDate(),
                subproject.getTotalEstimatedCost(),
                0.0, // initial actual cost
                subproject.isComplete(),
                subproject.getSubprojectDescription(),
                subproject.getHoursAllocated(),
                subproject.getPriority().toString()
        );
    }

    public Subproject readSubproject(int subprojectId) {
        String query = "SELECT * FROM subprojects WHERE subproject_id = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
    }

    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        String query = "SELECT * FROM subprojects WHERE project_id = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.query(query, rowMapper, projectId);
    }

    public void updateSubproject(int subprojectId, Subproject subproject) {
        String query = "UPDATE subprojects SET project_id = ?, subproject_name = ?, " +
                "start_date = ?, end_date = ?, total_estimated_cost = ?, " +
                "total_assigned_employees = ?, total_actual_cost = ?, hours_allocated = ?, " +
                "priority = ?, is_complete = ?, subproject_description = ? " +
                "WHERE subproject_id = ?";

        jdbcTemplate.update(query,
                subproject.getProjectId(),
                subproject.getSubprojectName(),
                subproject.getStartDate(),
                subproject.getEndDate(),
                subproject.getTotalEstimatedCost(),
                subproject.getTotalAssignedEmployees(),
                subproject.getTotalActualCost(),
                subproject.getHoursAllocated(),
                subproject.getPriority().toString(),
                subproject.isComplete(),
                subproject.getSubprojectDescription(),
                subprojectId
        );
    }

    public void deleteSubproject(int subprojectId) {
        String query = "DELETE FROM subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(query, subprojectId);
    }

    public List<Task> readAllTasksBySubproject(int subprojectId) {
        String query = "SELECT * FROM tasks WHERE subproject_id = ?";
        RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
        return jdbcTemplate.query(query, rowMapper, subprojectId);
    }

    public int calculateTotalActualCost(List<Task> listOfTasks) {
        int totalActualCost = 0;
        for (Task task : listOfTasks) {
            totalActualCost += task.getEstimatedCost();
        }
        return totalActualCost;
    }

    public int totalActualSubprojectHours(List<Task> listOfTasks) {
        int totalActualHours = 0;
        for (Task task : listOfTasks) {
            int taskDays = (int) (task.getEndDate().toEpochDay() - task.getStartDate().toEpochDay());
            totalActualHours += taskDays * task.getAssignedEmployees() * 8;  // 8 hours per workday
        }
        return totalActualHours;
    }

    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject) {
        int totalSubprojectEmployees = subproject.getTotalAssignedEmployees();
        int totalEmployeesInUse = 0;

        for (Task task : listOfTasks) {
            totalEmployeesInUse += task.getAssignedEmployees();
        }

        return totalSubprojectEmployees - totalEmployeesInUse;
    }
}