package ProjectPortal.Repository;

import java.time.LocalDate;
import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProjectRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterator<Double> iterator() {
        return null;
    }

    /**
     * Inserts a new project record into the `projects` database table.
     * @param project an object containing the project details to be stored, including
     *                company ID, user ID, project name, start date, end date, total estimated cost,
     *                total assigned employees, completion status, and project description.
     */
    public void createProject(Project project) {

        try {
            String query = "INSERT INTO projects (company_id, user_id, project_name, start_date, " +
                    "end_date, is_complete, " +
                    "project_description) VALUES (?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query,
                    project.getCompanyId(),
                    project.getUserId(),
                    project.getProjectName(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.isComplete(),
                    project.getProjectDescription()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create project", e);
        }
    }

    /**
     * Reads and retrieves a project from the database using the given project ID.
     * @param projectId the unique identifier of the project to be retrieved
     * @return the {@link Project} object corresponding to the given project ID,
     *         or throws an exception if no project is found
     */
    public Project readProject(int projectId) {
        try {
            String query = "SELECT * FROM projects WHERE project_id = ?;";
            RowMapper<Project> rowMapper = new BeanPropertyRowMapper<>(Project.class);
            return jdbcTemplate.queryForObject(query, rowMapper, projectId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of all projects from the database.
     * custom rowmapper
     * @return a list of {@link Project} objects representing all projects stored in the database.
     */
    public List<Project> readAllProjects() {
        try {
            String query = "SELECT * FROM projects";
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Project p = new Project();
                boolean completeValue = rs.getBoolean("is_complete");
                p.setProjectId(rs.getInt("project_id"));
                p.setProjectName(rs.getString("project_name"));
                p.setStartDate(rs.getDate("start_date").toLocalDate());
                p.setEndDate(rs.getDate("end_date").toLocalDate());
                p.setComplete(completeValue);
                p.setProjectDescription(rs.getString("project_description"));

                return p;
            });
        } catch (DataAccessException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Updates the details of an existing project in the database.
     * @param project    The Project object containing the updated details of the project.
     * @param projectId  The unique identifier of the project to be updated.
     */
    public void updateProject(Project project, int projectId) {
        try {
            String query = "UPDATE projects SET company_id = ?, project_name = ?, start_date = ?, " +
                    "end_date = ?, " +
                    "is_complete = ?, project_description = ? WHERE project_id = ?;";
            jdbcTemplate.update(query,
                    project.getCompanyId(),
                    project.getProjectName(),
                    project.getStartDate(),
                    project.getEndDate(),
                    project.isComplete(),
                    project.getProjectDescription(),
                    projectId
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update project", e);
        }
    }

    /**
     * Deletes a project from the database with the given project ID.
     * @param projectId the unique identifier of the project to be deleted
     */
    public void deleteProject(int projectId) {
        try {
            String query = "DELETE FROM projects WHERE project_id = ?";
            jdbcTemplate.update(query, projectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot delete project", e);
        }
    }


    public void markComplete(int projectId) {
        try {
            String sql = "UPDATE projects SET is_complete = true WHERE project_id = ?";
            jdbcTemplate.update(sql, projectId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot update project", e);
        }
    }

    /**
     * Formats a given LocalDate into a JavaScript-compatible date string representation.
     * The method constructs the string in the format: "new Date(year, month, day)".
     * Note that the month is adjusted by subtracting 1 to match JavaScript's zero-based month indexing.
     * @param date the LocalDate instance to be formatted.
     * @return a formatted string representing the date in JavaScript's Date format.
     */
    public String formatForJavaScript(LocalDate date) {
        return String.format("new Date(%d, %d, %d)",
                date.getYear(),
                date.getMonthValue() - 1,
                date.getDayOfMonth());
    }
}