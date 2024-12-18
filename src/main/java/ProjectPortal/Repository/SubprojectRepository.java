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

    /**
     * Creates a new subproject entry in the database using the provided Subproject object.
     * The method uses a prepared SQL query to insert details of the subproject into the database.
     * @param subproject the Subproject object containing the details of the subproject to be created.
     *                   This includes project ID, subproject name, start and end dates, estimated cost,
     *                   completion status, description, hours allocated, and priority.
     */
    public void createSubproject(Subproject subproject) {
        try {
            String query = "INSERT INTO subprojects (project_id, subproject_name, start_date, " +
                    "end_date," +
                    "is_complete, subproject_description, priority) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query,
                    subproject.getProjectId(),
                    subproject.getSubprojectName(),
                    subproject.getStartDate(),
                    subproject.getEndDate(),
                    subproject.isComplete(),
                    subproject.getSubprojectDescription(),
                    subproject.getPriority().toString()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create subproject", e);
        }
    }

    /**
     * Reads a subproject from the database based on the provided subproject ID.
     * @param subprojectId the unique identifier of the subproject to be retrieved
     * @return the Subproject object containing the details of the subproject
     */
    public Subproject readSubproject(int subprojectId) {
        try {
            String query = "SELECT * FROM subprojects WHERE subproject_id = ?";
            RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
            return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Reads all subprojects associated with a specific project ID from the database.
     * @param projectId the ID of the project whose subprojects are to be retrieved
     * @return a list of subprojects belonging to the specified project ID
     */
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
                s.setComplete(completeValue);
                s.setSubprojectDescription(rs.getString("subproject_description"));

                if (rs.getString("priority") != null) {
                    s.setPriority(rs.getString("priority"));
                }
                return s;
            }, projectId);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Updates the details of an existing subproject in the database using the provided
     * subproject information and its ID.
     * @param subprojectId the ID of the subproject to be updated
     * @param subproject   the updated subproject details including project ID, name,
     *                     start date, end date, estimated cost, total assigned employees,
     *                     actual cost, hours allocated, priority, completion status,
     *                     and subproject description
     */
    public void updateSubproject(int subprojectId, Subproject subproject) {
        try {
            String query = "UPDATE subprojects SET project_id = ?, subproject_name = ?, " +
                    "start_date = ?, end_date = ?," +
                    "priority = ?, is_complete = ?, subproject_description = ? " +
                    "WHERE subproject_id = ?";

            jdbcTemplate.update(query,
                    subproject.getProjectId(),
                    subproject.getSubprojectName(),
                    subproject.getStartDate(),
                    subproject.getEndDate(),
                    subproject.getPriority().toString(),
                    subproject.isComplete(),
                    subproject.getSubprojectDescription(),
                    subprojectId
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update subproject", e);
        }
    }

    /**
     * Deletes a subproject from the database based on the provided subproject ID.
     * @param subprojectId the unique identifier of the subproject to be deleted
     */
    public void deleteSubproject(int subprojectId) {
        try {
            String query = "DELETE FROM subprojects WHERE subproject_id = ?";
            jdbcTemplate.update(query, subprojectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot delete subproject", e);
        }
    }

    /**
     * Retrieves a list of all tasks associated with a specific subproject.
     * @param subprojectId the ID of the subproject for which tasks are to be retrieved.
     * @return a list of tasks belonging to the specified subproject.
     */
    public List<Task> readAllTasksBySubproject(int subprojectId) {
        try {
            String query = "SELECT * FROM tasks WHERE subproject_id = ?";
            RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
            return jdbcTemplate.query(query, rowMapper, subprojectId);
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Calculates the total actual cost of all tasks in the provided list.
     * @param listOfTasks a list of Task objects whose estimated costs will be summed
     * @return the total actual cost as an integer by summing the estimated costs of all tasks
     */
    public int calculateTotalActualCost(List<Task> listOfTasks) {
        int totalActualCost = 0;
        for (Task task : listOfTasks) {
            totalActualCost += task.getEstimatedCost();
        }
        return totalActualCost;
    }

    /**
     * Marks a subproject as complete by updating its status in the database.
     *
     * @param subprojectId The ID of the subproject to be marked as complete.
     */
    public void markComplete(int subprojectId) {
        try {
            String sql = "UPDATE subprojects SET is_complete = true WHERE subproject_id = ?";
            jdbcTemplate.update(sql, subprojectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update subproject", e);
        }
    }
}