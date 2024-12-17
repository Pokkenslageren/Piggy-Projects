package ProjectPortal.Repository;


import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.Task;
import org.springframework.dao.DataAccessException;
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
        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create subproject", e);
        }
    }

    public Subproject readSubproject(int subprojectId) {
        try {
            String query = "SELECT * FROM subprojects WHERE subproject_id = ?";
            RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
            return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        try {
            String query = "SELECT * FROM subprojects WHERE project_id = ?";
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Subproject s = new Subproject();
                boolean completeValue = rs.getBoolean("is_complete");
                s.setSubprojectId(rs.getInt("subproject_id"));
                s.setProjectId(rs.getInt("project_id"));
                s.setSubprojectName(rs.getString("subproject_name"));
                s.setStartDate(rs.getDate("start_date").toLocalDate());
                s.setEndDate(rs.getDate("end_date").toLocalDate());
                s.setTotalEstimatedCost(rs.getDouble("total_estimated_cost"));
                s.setTotalActualCost(rs.getDouble("total_actual_cost"));
                s.setTotalAssignedEmployees(rs.getInt("total_assigned_employees"));
                s.setComplete(completeValue);
                s.setSubprojectDescription(rs.getString("subproject_description"));
                s.setHoursAllocated(rs.getInt("hours_allocated"));
                if (rs.getString("priority") != null) {
                    s.setPriority(rs.getString("priority"));
                }
                return s;
            }, projectId);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    public void updateSubproject(int subprojectId, Subproject subproject) {
        try {
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
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update subproject", e);
        }
    }

    public void deleteSubproject(int subprojectId) {
        try {
            String query = "DELETE FROM subprojects WHERE subproject_id = ?";
            jdbcTemplate.update(query, subprojectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot delete subproject", e);
        }
    }

    public List<Task> readAllTasksBySubproject(int subprojectId) {
        try {
            String query = "SELECT * FROM tasks WHERE subproject_id = ?";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
            return jdbcTemplate.query(query, rowMapper, subprojectId);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
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

    public void markComplete(int subprojectId) {
        try {
            String sql = "UPDATE subprojects SET is_complete = true WHERE subproject_id = ?";
            jdbcTemplate.update(sql, subprojectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update subproject", e);
        }
    }
}