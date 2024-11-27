package ProjectPlanner.Repository;

import ProjectPlanner.Model.Task;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final String url = "jdbc:mysql://localhost:3306/projectplanner";
    private final String username = "root";
    private final String password = "password";

    /**
     * Find all tasks
     * @return
     */
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tasks.add(mapRowToTask(resultSet));
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
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, taskId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRowToTask(resultSet));
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
    public Task save(Task task) {
        String query = "INSERT INTO tasks (taskId, projectId, subprojectId, taskName, assignedEmployees, estimatedCost, startDate, endDate, isComplete, taskDescription) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getTaskId());
            preparedStatement.setString(2, task.getProjectId());
            preparedStatement.setString(3, task.getSubprojectId());
            preparedStatement.setString(4, task.getTaskName());
            preparedStatement.setInt(5, task.getAssignedEmployees());
            preparedStatement.setDouble(6, task.getEstimatedCost());
            preparedStatement.setString(7, task.getStartDate());
            preparedStatement.setString(8, task.getEndDate());
            preparedStatement.setBoolean(9, task.getIsComplete());
            preparedStatement.setString(10, task.getTaskDescription());
            preparedStatement.executeUpdate();

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

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, taskId);
            preparedStatement.executeUpdate();

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

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, task.getProjectId());
            preparedStatement.setString(2, task.getSubprojectId());
            preparedStatement.setString(3, task.getTaskName());
            preparedStatement.setInt(4, task.getAssignedEmployees());
            preparedStatement.setDouble(5, task.getEstimatedCost());
            preparedStatement.setString(6, task.getStartDate());
            preparedStatement.setString(7, task.getEndDate());
            preparedStatement.setBoolean(8, task.getIsComplete());
            preparedStatement.setString(9, task.getTaskDescription());
            preparedStatement.setString(10, task.getTaskId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return task;
    }

    /**
     * Mapping database-row to a Task-object
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Task mapRowToTask(ResultSet resultSet) throws SQLException {
        return new Task(
                resultSet.getString("projectId"),
                resultSet.getString("subprojectId"),
                resultSet.getString("taskId"),
                resultSet.getString("taskName"),
                resultSet.getInt("assignedEmployees"),
                resultSet.getDouble("estimatedCost"),
                resultSet.getString("startDate"),
                resultSet.getString("endDate"),
                resultSet.getBoolean("isComplete"),
                resultSet.getString("taskDescription")
        );
    }
}
