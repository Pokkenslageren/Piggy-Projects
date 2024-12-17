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

    /**
     * Creates a new subproject entry in the database using the provided Subproject object.
     * The method uses a prepared SQL query to insert details of the subproject into the database.
     * @param subproject the Subproject object containing the details of the subproject to be created.
     *                   This includes project ID, subproject name, start and end dates, estimated cost,
     *                   completion status, description, hours allocated, and priority.
     */
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

    /**
     * Reads a subproject from the database based on the provided subproject ID.
     * @param subprojectId the unique identifier of the subproject to be retrieved
     * @return the Subproject object containing the details of the subproject
     */
    public Subproject readSubproject(int subprojectId) {
        String query = "SELECT * FROM subprojects WHERE subproject_id = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
    }

    /**
     * Reads all subprojects associated with a specific project ID from the database.
     * @param projectId the ID of the project whose subprojects are to be retrieved
     * @return a list of subprojects belonging to the specified project ID
     */
    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
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

    /**
     * Deletes a subproject from the database based on the provided subproject ID.
     * @param subprojectId the unique identifier of the subproject to be deleted
     */
    public void deleteSubproject(int subprojectId) {
        String query = "DELETE FROM subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(query, subprojectId);
    }

    /**
     * Retrieves a list of all tasks associated with a specific subproject.
     * @param subprojectId the ID of the subproject for which tasks are to be retrieved.
     * @return a list of tasks belonging to the specified subproject.
     */
    public List<Task> readAllTasksBySubproject(int subprojectId) {
        String query = "SELECT * FROM tasks WHERE subproject_id = ?";
        RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
        return jdbcTemplate.query(query, rowMapper, subprojectId);
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
     * Calculates the total number of actual work hours for all the given tasks in the subproject.
     * Each task's total hours are calculated based on the number of days between its start and end dates,
     * the number of assigned employees, and an assumption of an eight-hour workday.
     * @param listOfTasks a list of Task objects representing all tasks in the subproject
     * @return the total number of actual work hours for all tasks in the provided list
     */
    public int totalActualSubprojectHours(List<Task> listOfTasks) {
        int totalActualHours = 0;
        for (Task task : listOfTasks) {
            int taskDays = (int) (task.getEndDate().toEpochDay() - task.getStartDate().toEpochDay());
            totalActualHours += taskDays * task.getAssignedEmployees() * 8;  // 8 hours per workday
        }
        return totalActualHours;
    }

    /**
     * Calculates the total number of available employees for a given subproject
     * by subtracting the number of employees currently assigned to tasks
     * from the total employees assigned to the subproject.
     * @param listOfTasks the list of tasks within the subproject, each containing the number of assigned employees
     * @param subproject the subproject for which the total available employees are to be calculated
     * @return the total number of employees available for the specified subproject after accounting for task assignments
     */
    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject) {
        int totalSubprojectEmployees = subproject.getTotalAssignedEmployees();
        int totalEmployeesInUse = 0;

        for (Task task : listOfTasks) {
            totalEmployeesInUse += task.getAssignedEmployees();
        }

        return totalSubprojectEmployees - totalEmployeesInUse;
    }

    /**
     * Marks a subproject as complete by updating its status in the database.
     *
     * @param subprojectId The ID of the subproject to be marked as complete.
     */
    public void markComplete(int subprojectId) {
        String sql = "UPDATE subprojects SET is_complete = true WHERE subproject_id = ?";
        jdbcTemplate.update(sql, subprojectId);
    }
}