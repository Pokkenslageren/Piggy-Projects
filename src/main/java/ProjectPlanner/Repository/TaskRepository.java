package ProjectPlanner.Repository;

import ProjectPlanner.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Task> getAllTasks() {
        String query = "SELECT * FROM tasks;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Task.class);
        return jdbcTemplate.query(query, rowMapper);
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

    /**
     * Create a new task with variables
     * @param taskName
     * @param taskId
     * @param assignedEmployees
     * @param estimatedCost
     * @param startDate
     * @param endDate
     * @param isComplete
     * @param description
     */
    public void saveTask(String taskName, int taskId, int assignedEmployees, int estimatedCost, int startDate, int endDate, boolean isComplete, String description) {
        String query = "INSERT INTO tasks (task_name, task_id, assigned_employees, estimated_cost, start_date, end_date, is_complete, task_description)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, taskName, taskId, assignedEmployees, estimatedCost, startDate, endDate, isComplete, description);
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
    public void updateTask(String taskName, int taskId, int assignedEmployees, int estimatedCost, int startDate, int endDate, boolean isComplete, String description){
        String query = "UPDATE tasks" +
                "SET task_name = ?, " +
                "task_id = ?, " +
                "assigned_employees = ?, " +
                "estimated_cost = ?, " +
                "start_date = ?, " +
                "end_date = ?, " +
                "is_complete = ?, " +
                "task_description = ? " +
                "WHERE task_id = ?;";
        jdbcTemplate.update(query, taskName, taskId, assignedEmployees, estimatedCost, startDate, endDate, isComplete, description);
    }

    /**
     *
     * @param task
     */
    /*
    public void update(Task task) {
        String query = "UPDATE tasks SET task_name = ?, assigned_employees = ?, estimated_cost = ?, start_date = ?, end_date = ?, is_complete = ?, task_description = ? WHERE task_id = ?;";
        jdbcTemplate.update(query, task.getTaskName(), task.getAssignedEmployees(), task.getEstimatedCost(), task.getStartDate(), task.getEndDate(), task.getIsComplete(), task.getTaskDescription(), Integer.parseInt(task.getTaskId()));
    }

     */


    /**
     * Mapping database-row to a Task-object
     * @param rs
     * @return
     * @throws SQLException
     */
    private Task mapRowToTask(ResultSet rs) throws SQLException {
        return new Task(
                rs.getInt("projectId"),
                rs.getInt("subprojectId"),
                rs.getInt("taskId"),
                rs.getString("taskName"),
                rs.getInt("assignedEmployees"),
                rs.getDouble("estimatedCost"),
                rs.getInt("startDate"),
                rs.getInt("endDate"),
                rs.getBoolean("isComplete"),
                rs.getString("taskDescription")
        );
    }
}
