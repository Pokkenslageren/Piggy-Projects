package ProjectPlanner.Repository;

import ProjectPlanner.Model.Task;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final String database = "jdbc:mysql://localhost:3306/projectplanner";
    private final String dbUsername = "root";
    private final String dbPassword = "password";

    // application.properties ????

    /**
     * Find all tasks
     * @return
     */
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";

        try (Connection connection = DriverManager.getConnection(database, dbUsername, dbPassword);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tasks.add(mapRowToTask(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Find a task by ID
     * @param taskId
     * @return
     */
    public Optional<Task> findById(String taskId) {
        String query = "SELECT * FROM tasks WHERE taskId = ?";
        try (Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, taskId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToTask(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Create new task
     * @param task
     * @return
     */
    public Task saveTask(Task task) {
        String query = "INSERT INTO tasks (taskId, projectId, subprojectId, taskName, assignedEmployees, estimatedCost, startDate, endDate, isComplete, taskDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, task.getTaskId());
            pstmt.setString(2, task.getProjectId());
            pstmt.setString(3, task.getSubprojectId());
            pstmt.setString(4, task.getTaskName());
            pstmt.setInt(5, task.getAssignedEmployees());
            pstmt.setDouble(6, task.getEstimatedCost());
            pstmt.setString(7, task.getStartDate());
            pstmt.setString(8, task.getEndDate());
            pstmt.setBoolean(9, task.getIsComplete());
            pstmt.setString(10, task.getTaskDescription());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    /**
     * Delete a task by ID
     * @param taskId
     */
    public void deleteById(String taskId) {
        String query = "DELETE FROM tasks WHERE taskId = ?";

        try (Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, taskId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a task
     * @param task
     * @return
     */
    public Task update(Task task) {
        String query = "UPDATE tasks SET projectId = ?, subprojectId = ?, taskName = ?, assignedEmployees = ?, estimatedCost = ?, startDate = ?, endDate = ?, isComplete = ?, taskDescription = ? WHERE taskId = ?";

        try (Connection conn = DriverManager.getConnection(database, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, task.getProjectId());
            pstmt.setString(2, task.getSubprojectId());
            pstmt.setString(3, task.getTaskName());
            pstmt.setInt(4, task.getAssignedEmployees());
            pstmt.setDouble(5, task.getEstimatedCost());
            pstmt.setString(6, task.getStartDate());
            pstmt.setString(7, task.getEndDate());
            pstmt.setBoolean(8, task.getIsComplete());
            pstmt.setString(9, task.getTaskDescription());
            pstmt.setString(10, task.getTaskId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    /**
     * Mapping database-row to a Task-object
     * @param rs
     * @return
     * @throws SQLException
     */
    private Task mapRowToTask(ResultSet rs) throws SQLException {
        return new Task(
                rs.getString("projectId"),
                rs.getString("subprojectId"),
                rs.getString("taskId"),
                rs.getString("taskName"),
                rs.getInt("assignedEmployees"),
                rs.getDouble("estimatedCost"),
                rs.getString("startDate"),
                rs.getString("endDate"),
                rs.getBoolean("isComplete"),
                rs.getString("taskDescription")
        );
    }
}
