package ProjectPlanner.Repository;


import ProjectPlanner.Model.Subproject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.*;

@Repository
public class SubprojectRepository extends ProjectRepository{

    private final JdbcTemplate jdbcTemplate;

    public SubprojectRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createSubproject(Subproject subproject) {
        String query = "INSERT INTO subprojects (name, start_date, end_date, project_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, subproject.getSubprojectName(), subproject.getStartDate(), subproject.getEndDate(), subproject.getSubprojectId());
    }

    // Read a single subproject by ID
    public Subproject readSubproject(int subprojectId) {
        String query = "SELECT * FROM subprojects WHERE subprojectid = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.queryForObject(query, rowMapper, subprojectId);
    }

    // Read all subprojects for a given project ID
    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        String query = "SELECT * FROM subprojects WHERE project_id = ?";
        RowMapper<Subproject> rowMapper = new BeanPropertyRowMapper<>(Subproject.class);
        return jdbcTemplate.query(query, rowMapper, projectId);
    }

    // Update an existing subproject
    public void updateSubproject(int subprojectId, Subproject updatedSubproject) {
        String query = "UPDATE subprojects SET name = ?, start_date = ?, end_date = ?, project_id = ? WHERE subprojectid = ?";
        jdbcTemplate.update(query, updatedSubproject.getSubprojectName(), updatedSubproject.getStartDate(), updatedSubproject.getEndDate(), updatedSubproject.getSubprojectId(), subprojectId);
    }

    // Delete a subproject by ID
    public void deleteSubproject(int subprojectId) {
        String query = "DELETE FROM subprojects WHERE subprojectid = ?";
        jdbcTemplate.update(query, subprojectId);
    }
}
