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
    public List<Task> getAllTasks(int projectId) {
        String query = "SELECT * FROM tasks WHERE project_id=?;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Task.class);
        return jdbcTemplate.query(query, rowMapper, projectId);
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
     *
     * @param task
     */
    public void saveTask(Task task) {
        String query = "INSERT INTO tasks (get_task_name, get_task_id, get_assigned_employees, get_estimated_cost, get_start_date, get_end_date, get_is_complete, get_task_description)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, task);
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
/*    private Task mapRowToTask(ResultSet rs) throws SQLException {
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
    }*/
}
