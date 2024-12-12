package ProjectPortal.Service;

import ProjectPortal.Model.Task;
import ProjectPortal.Repository.SubprojectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ProjectPortal.Model.Subproject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprojectService {
    private final SubprojectRepository subprojectRepository;

    @Autowired
    public SubprojectService(SubprojectRepository subprojectRepository) {
        this.subprojectRepository = subprojectRepository;
    }

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

    public int totalActualSubprojectHours(List<Task> listOfTasks){
        return subprojectRepository.totalActualSubprojectHours(listOfTasks);
    }

    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject){
        return subprojectRepository.calculateTotalAvailableEmployees(listOfTasks, subproject);
    }
}
