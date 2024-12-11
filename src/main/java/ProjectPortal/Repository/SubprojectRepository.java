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

    /**
     * constructor passes JdbcTemplate to parent class
     *
     * @param jdbcTemplate
     */
    public SubprojectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Create new Subproject in subprojects table
     *
     * @param subproject
     */
    public void createSubproject(Subproject subproject) {
        String query = "INSERT INTO subprojects (name, start_date, end_date, project_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, subproject.getSubprojectName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getSubprojectId());
    }

    /**
     * reads a single subproject by subprojectId
     *
     * @param subprojectId
     * @return
     */
    public Subproject readSubproject(int subprojectId) {
        String query = "SELECT * FROM subprojects WHERE subprojectid = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
    }

    /**
     * Reads all subprojects belonging to a single project by projectId
     *
     * @param projectId
     * @return
     */
    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        String query = "SELECT * FROM subprojects WHERE project_id = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.query(query, rowMapper, projectId);
    }

    /**
     * Updates an existing subproject
     *
     * @param subprojectId
     * @param updatedSubproject
     */
    public void updateSubproject(int subprojectId, Subproject updatedSubproject) {
        String query = "UPDATE subprojects SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE subprojectid = ?";
        jdbcTemplate.update(query, updatedSubproject.getSubprojectName(), updatedSubproject.getStartDate(), updatedSubproject.getEndDate(), updatedSubproject.getSubprojectId(), subprojectId);
    }

    /**
     * deletes subproject by subproject id
     *
     * @param subprojectId
     */
    public void deleteSubproject(int subprojectId) {
        String query = "DELETE FROM subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(query, subprojectId);
    }

    public List<Task> readAllTasksBySubproject(int subprojectId) {
        String query = "SELECT * FROM tasks WHERE subprojectid = subproject_id = ?";
        RowMapper<Task> rowMapper = new BeanPropertyRowMapper<>(Task.class);
        return jdbcTemplate.query(query, rowMapper, subprojectId);
    }

    /**
     * Calculates number of available employees based on assigned employees in tasks
     *
     * @param listOfTasks A list of all tasks associated with the subproject
     * @param subproject  The given subproject
     * @return The number of employees not assigned to a task.
     */
    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject) {
        var iterator = listOfTasks.iterator();
        int totalSubprojectEmployees = subproject.getTotalAssignedEmployees();
        int totalEmployeesInUse = 0;
        while (iterator.hasNext()) {
            totalEmployeesInUse = totalEmployeesInUse + iterator.next().getAssignedEmployees();
        }
        return totalSubprojectEmployees - totalEmployeesInUse;
    }

    public int totalActualTaskHours(Task task) {
        int numberOfEmployees = task.getAssignedEmployees();
        return (task.getEndDate().getDayOfYear() - task.getStartDate().getDayOfYear()) * numberOfEmployees * 8;
    }

    public int totalActualSubprojectHours(List<Task> listOfTasks) {
        var iterator = listOfTasks.iterator();
        int totalActualSubprojectHours = 0;
        while (iterator.hasNext()) {
            totalActualSubprojectHours += totalActualTaskHours(iterator.next());
        }
        return totalActualSubprojectHours;
    }

    public void setDynamicValuesSubproject(List<Subproject> listOfSubprojects) {
        var iterator = listOfSubprojects.iterator();
        while (iterator.hasNext()) {
            List<Task> listOfTasks = readAllTasksBySubproject(iterator.next().getSubprojectId());
            iterator.next().setTotalAvailiableEmployees(calculateTotalAvailableEmployees(listOfTasks, iterator.next()));
            /*iterator.next().setTotalActualCost();*/
        }
    }
}
