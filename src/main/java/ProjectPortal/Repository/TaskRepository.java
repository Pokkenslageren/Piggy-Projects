package ProjectPortal.Repository;

import ProjectPortal.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.sql.*;
import java.util.List;

@Repository
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Get all tasks
     * @return
     */
    public List<Task> readTasksBySubprojectId(int subprojectId) {
        String query = "SELECT * FROM tasks WHERE subproject_id = ?;";
        RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
        return jdbcTemplate.query(query, rowMapper, subprojectId);
    }

    /**
     * Get a single task by ID
     * @param id
     * @return
     */
    public Task getTaskById(int id) {
        String query = "SELECT * FROM tasks WHERE id = ?;";
        RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
        return jdbcTemplate.queryForObject(query, rowMapper, id);
    }

    public void createTask(Task task) {
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
    }

    /**
     * Deletes a task by ID
     * @param taskId
     */
    public void deleteTaskById(int taskId) {
        String query = "DELETE FROM tasks WHERE task_id = ?;";
        jdbcTemplate.update(query, taskId);
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

    /**
     * Checks if enough hours have been allocated to the task
     * @param task
     * @return
     */
    public boolean sufficientHours(Task task){
        int totalTaskHours = totalTaskHours(task);
        return (task.getHoursAllocated() > totalTaskHours );
    }

    public void markComplete(int taskId) {
        String sql = "UPDATE tasks SET is_complete = true WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }

}
