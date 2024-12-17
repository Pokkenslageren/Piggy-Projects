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
     * Retrieves a list of tasks associated with the specified subproject ID.
     * @param subprojectId the ID of the subproject for which tasks are to be retrieved
     * @return a list of {@code Task} objects associated with the given subproject ID
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
     * Retrieves a task from the database based on the specified task ID.
     * @param id the ID of the task to retrieve
     * @return the Task object corresponding to the provided ID
     *         or throws an exception if no task is found
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

    /**
     * Inserts a new task into the database with the provided task details.
     * @param task the Task object containing the details of the task to be added. This includes:
     *             subproject ID, task name, start date, end date, estimated cost, assigned employees,
     *             completion status, task description, hours allocated, and priority.
     */
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
     * Deletes a task from the database by its unique identifier.
     * @param taskId the unique identifier of the task to be deleted
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
     * Updates an existing task in the database with the provided details.
     * @param taskName          The name of the task to update.
     * @param taskId            The unique identifier of the task to update.
     * @param assignedEmployees The number of employees assigned to the task.
     * @param estimatedCost     The estimated cost of the task.
     * @param startDate         The start date of the task.
     * @param endDate           The end date of the task.
     * @param isComplete        A boolean value indicating whether the task is complete.
     * @param description       A description of the task.
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
     * Calculates the total number of work hours required for a given task.
     * The calculation is based on the task duration in days and the number of assigned employees,
     * assuming an 8-hour work day.
     * @param task the Task object containing details such as start date, end date, and assigned employees
     * @return the total number of work hours for the task
     */
    public int totalTaskHours(Task task){
        int taskDays = task.getEndDate().getDayOfYear() - task.getStartDate().getDayOfYear();
        return taskDays * task.getAssignedEmployees() * 8;
    }


    /**
     * Determines if the task has sufficient allocated hours compared to the total required hours.
     * @param task the task whose hours allocation is being checked
     * @return true if the allocated hours for the task exceed the total task hours, false otherwise
     */
    public boolean sufficientHours(Task task){
        int totalTaskHours = totalTaskHours(task);
        return (task.getHoursAllocated() > totalTaskHours );
    }


    /**
     * Marks a task as complete in the database by setting the `is_complete`
     * field to true for the task with the specified task ID.
     * @param taskId the ID of the task to mark as complete
     */
    public void markComplete(int taskId) {
        try {
            String sql = "UPDATE tasks SET is_complete = true WHERE task_id = ?";
            jdbcTemplate.update(sql, taskId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update task", e);
        }
    }

}
