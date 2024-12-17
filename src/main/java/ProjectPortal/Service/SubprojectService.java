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
     * Creates a new subproject with initial settings and stores it in the repository.
     * @param subproject the Subproject object to be created and stored
     */
    public void createSubproject(Subproject subproject) {
        subproject.setTotalAssignedEmployees(0);
        subprojectRepository.createSubproject(subproject);
    }

    /**
     * Reads a specific subproject by its unique identifier and updates its calculations.
     * @param subprojectId the unique identifier of the subproject to be read
     * @return the subproject associated with the given identifier, with updated calculations
     */
    public Subproject readSubproject(int subprojectId) {
        Subproject subproject = subprojectRepository.readSubproject(subprojectId);
        updateSubprojectCalculations(subproject);
        return subproject;
    }

    /**
     * Updates the details of a subproject identified by its ID.
     * @param subprojectId the ID of the subproject to be updated
     * @param subproject the updated subproject object containing new details
     */
    public void updateSubproject(int subprojectId, Subproject subproject) {
        subprojectRepository.updateSubproject(subprojectId, subproject);
    }

    /**
     * Deletes a subproject based on the provided subproject ID.
     * @param subprojectId the unique identifier of the subproject to delete
     */
    public void deleteSubproject(int subprojectId) {
        subprojectRepository.deleteSubproject(subprojectId);
    }

    /**
     * Retrieves a list of all subprojects associated with the specified project ID.
     * Additionally, it updates the calculations for each subproject before returning the list.
     * @param projectId the unique identifier of the project whose subprojects are to be retrieved
     * @return a list of subprojects belonging to the specified project
     */
    public List<Subproject> readAllSubprojectsByProjectId(int projectId) {
        List<Subproject> subprojects = subprojectRepository.readAllSubprojectsByProjectId(projectId);
        for (Subproject subproject : subprojects) {
            updateSubprojectCalculations(subproject);
        }
        return subprojects;
    }

    /**
     * Marks the specified subproject as complete by delegating the operation
     * to the underlying repository.
     * @param subprojectId The ID of the subproject to mark as complete.
     */
    public void markComplete(int subprojectId) {
        subprojectRepository.markComplete(subprojectId);
    }

    /**
     * Updates the subproject's calculated fields based on its associated tasks.
     * It calculates and updates the total number of assigned employees and
     * the total actual cost based on the associated tasks of the given subproject.
     * @param subproject the subproject whose calculated fields are to be updated
     */
    public void updateSubprojectCalculations(Subproject subproject) {
        List<Task> tasks = taskService.readTasksBySubprojectId(subproject.getSubprojectId());
        int totalEmployees = calculateTotalEmployees(tasks);
        subproject.setTotalAssignedEmployees(totalEmployees);

        double totalActualCost = tasks.stream()
                .mapToDouble(Task::getEstimatedCost)
                .sum();
        subproject.setTotalActualCost(totalActualCost);
    }

    /**
     * Calculates the total number of employees assigned to all tasks in the provided list.
     * @param tasks the list of tasks for which the total assigned employees are to be calculated
     * @return the total number of employees assigned to all tasks
     */
    private int calculateTotalEmployees(List<Task> tasks) {
        return tasks.stream()
                .mapToInt(Task::getAssignedEmployees)
                .sum();
    }
    /**
     * Calculates the total actual hours spent on subproject tasks.
     * @param listOfTasks a list of tasks associated with the subproject
     * @return the total actual hours worked across all specified tasks
     */
    //?
    public int totalActualSubprojectHours(List<Task> listOfTasks){
        return subprojectRepository.totalActualSubprojectHours(listOfTasks);
    }

    /**
     * Calculates the total number of available employees for a specific subproject based on a list of tasks.
     * @param listOfTasks the list of tasks associated with the subproject
     * @param subproject the subproject for which the available employees are calculated
     * @return the total number of available employees for the given subproject
     */
    public int calculateTotalAvailableEmployees(List<Task> listOfTasks, Subproject subproject){
        return subprojectRepository.calculateTotalAvailableEmployees(listOfTasks, subproject);
    }

    /**
     * Retrieves all tasks associated with a specific subproject.
     * @param subprojectId the unique identifier of the subproject whose tasks are to be retrieved
     * @return a list of tasks that belong to the specified subproject
     */
    public List<Task> readAllTasksBySubproject(int subprojectId){
        return subprojectRepository.readAllTasksBySubproject(subprojectId);
    }

    /**
     * Calculates the total actual cost for a given list of tasks.
     * @param listOfTasks the list of tasks for which the total actual cost is to be calculated
     * @return the total actual cost of the provided tasks
     */
    public int calculateTotalActualCost(List<Task> listOfTasks){
        return subprojectRepository.calculateTotalActualCost(listOfTasks);
    }
}
