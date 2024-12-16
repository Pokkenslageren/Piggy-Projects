package ProjectPortal.Service;

import ProjectPortal.Model.Task;
import ProjectPortal.Service.TaskService;
import ProjectPortal.Repository.SubprojectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ProjectPortal.Model.Subproject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprojectService {
    private final SubprojectRepository subprojectRepository;
    private final TaskService taskService;

    @Autowired
    public SubprojectService(SubprojectRepository subprojectRepository, TaskService taskService) {
        this.subprojectRepository = subprojectRepository;
        this.taskService = taskService;
    }

    /**
     * create subproject
     * @param subproject
     */
    public void createSubproject(Subproject subproject) {
        subproject.setTotalAssignedEmployees(0);
        subprojectRepository.createSubproject(subproject);
    }


    public Subproject readSubproject(int subprojectId) {
        Subproject subproject = subprojectRepository.readSubproject(subprojectId);
        updateSubprojectCalculations(subproject);
        return subproject;
    }

    /**
     * Deletes subproject by subprojectid
     * @param subprojectId
     */
    public void deleteSubproject(int subprojectId) {
        subprojectRepository.deleteSubproject(subprojectId);
    }

    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        List<Subproject> subprojects = subprojectRepository.readAllSubprojectsByProjectId(projectId);
        for (Subproject subproject : subprojects) {
            updateSubprojectCalculations(subproject);
        }
        return subprojects;
    }

    public void updateSubprojectCalculations(Subproject subproject) {
        List<Task> tasks = taskService.readTasksBySubprojectId(subproject.getSubprojectId());
        int totalEmployees = calculateTotalEmployees(tasks);
        subproject.setTotalAssignedEmployees(totalEmployees);

        double totalActualCost = tasks.stream()
                .mapToDouble(Task::getEstimatedCost)
                .sum();
        subproject.setTotalActualCost(totalActualCost);
    }

    private int calculateTotalEmployees(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(Task::getAssignedEmployees)
                .sum();
    }

    public int totalActualSubprojectHours(List<Task> listOfTasks){
        return subprojectRepository.totalActualSubprojectHours(listOfTasks);
    }

    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject){
        return subprojectRepository.calculateTotalAvailableEmployees(listOfTasks, subproject);
    }

    public List<Task> readAllTasksBySubproject(int subprojectId){
        return subprojectRepository.readAllTasksBySubproject(subprojectId);
    }

    public int calculateTotalActualCost(List<Task> listOfTasks){
        return subprojectRepository.calculateTotalActualCost(listOfTasks);
    }
}
