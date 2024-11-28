package ProjectPlanner.Service;

import ProjectPlanner.Repository.SubprojectRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import ProjectPlanner.Model.Subproject;
import org.springframework.stereotype.Service;

import java.util.List;

public class SubprojectService {

    private final SubprojectRepository subprojectRepository = new SubprojectRepository(new JdbcTemplate());

    public void createSubproject(Subproject subproject) {
        subprojectRepository.createSubproject(subproject);
    }

    public Subproject readSubproject(int subprojectId) {
        return subprojectRepository.readSubproject(subprojectId);
    }

    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        return  subprojectRepository.readAllSubprojectsByProjectId(projectId);
    }

    public void updateSubproject(int subprojectId, Subproject subproject) {
        subprojectRepository.updateSubproject(subprojectId, subproject);
    }

    public void deleteSubproject(int subprojectId) {
        subprojectRepository.deleteSubproject(subprojectId);
    }

} 
