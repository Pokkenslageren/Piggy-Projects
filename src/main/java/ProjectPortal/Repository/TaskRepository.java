package ProjectPortal.Repository;

import ProjectPortal.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.sql.*;
import java.util.Collections;
import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get all tasks belonging to subproject id
     * @return
     */
    public List<Task> readTasksBySubprojectId(int subprojectId) {
        try {
            String query = "SELECT * FROM tasks WHERE subproject_id = ?;";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
            return jdbcTemplate.query(query, rowMapper, subprojectId);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Get a single task by ID
     * @param id
     * @return
     */
    public Task getTaskById(int id) {
        try {
            String query = "SELECT * FROM tasks WHERE task_id = ?;";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
            return jdbcTemplate.queryForObject(query, rowMapper, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void createTask(Task task) {
        try {
            String query = "INSERT INTO tasks (subproject_id, task_name, start_date, end_date, " +
                    "estimated_cost, assigned_employees, is_complete, task_description, " +
                    "hours_allocated, priority) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query,
                    task.getSubprojectId(),
                    task.getTaskName(),
                    task.getStartDate(),
                    task.getEndDate(),
                    task.getEstimatedCost(),
                    task.getAssignedEmployees(),
                    task.getIsComplete(),
                    task.getTaskDescription(),
                    task.getHoursAllocated(),
                    task.getPriority().toString()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create task", e);
        }
    }

    /**
     * Deletes a task by ID
     * @param taskId
     */
    public void deleteTaskById(int taskId) {
        try {
            String query = "DELETE FROM tasks WHERE task_id = ?;";
            jdbcTemplate.update(query, taskId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot delete task", e);
        }
    }

    /**
     * Update a task
     * @param taskName
     * @param taskId
     * @param assignedEmployees
     * @param estimatedCost
     * @param startDate
     * @param endDate
     * @param isComplete
     * @param description
     */
    public void updateTask(String taskName, int taskId, int assignedEmployees, int estimatedCost, LocalDate startDate, LocalDate endDate, boolean isComplete, String description){
        try {
            String query = "UPDATE tasks " +
                    "SET task_name = ?, " +
                    "task_id = ?, " +
                    "assigned_employees = ?, " +
                    "estimated_cost = ?, " +
                    "start_date = ?, " +
                    "end_date = ?, " +
                    "is_complete = ?, " +
                    "task_description = ? " +
                    "WHERE task_id = ?;";
            jdbcTemplate.update(query, taskName, taskId, assignedEmployees, estimatedCost, startDate, endDate, isComplete, description, taskId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update task", e);
        }
    }

    /**
     * Computes the total number of hours as a function of number of days and number of employees
     * assuming eight-hour work days for each employee
     * @param task
     * @return
     */
    public int totalTaskHours(Task task){
        int taskDays = task.getEndDate().getDayOfYear() - task.getStartDate().getDayOfYear();
        return taskDays * task.getAssignedEmployees() * 8;
    }


    public void markComplete(int taskId) {
        try {
            String sql = "UPDATE tasks SET is_complete = true WHERE task_id = ?";
            jdbcTemplate.update(sql, taskId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update task", e);
        }
    }

}
