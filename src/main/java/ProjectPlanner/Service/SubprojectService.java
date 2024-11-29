package ProjectPlanner.Service;

import ProjectPlanner.Repository.SubprojectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import ProjectPlanner.Model.Subproject;
import org.springframework.stereotype.Service;

import java.util.List;

public class SubprojectService {

    private final SubprojectRepository subprojectRepository = new SubprojectRepository(new JdbcTemplate());

    /**
     * create subproject
     * @param subproject
     */
    public void createSubproject(Subproject subproject) {
        subprojectRepository.createSubproject(subproject);
    }

    /**
     * read a single subproject by id
     * @param subprojectId
     * @return
     */
    public Subproject readSubproject(int subprojectId) {
        return subprojectRepository.readSubproject(subprojectId);
    }

    /**
     * reads all subproject belonging to a single projectId
     * @param projectId
     * @return
     */
    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        return  subprojectRepository.readAllSubprojectsByProjectId(projectId);
    }

    /**
     * updates subproject
     * @param subprojectId
     * @param subproject
     */
    public void updateSubproject(int subprojectId, Subproject subproject) {
        subprojectRepository.updateSubproject(subprojectId, subproject);
    }

    /**
     * Deletes subproject by subprojectid
     * @param subprojectId
     */
    public void deleteSubproject(int subprojectId) {
        subprojectRepository.deleteSubproject(subprojectId);
    }

}
