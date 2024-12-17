package ProjectPortal.Service;

import ProjectPortal.Model.Task;
import ProjectPortal.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves a list of tasks associated with a specific subproject.
     * @param subprojectId the ID of the subproject whose tasks are to be retrieved
     * @return a list of Task objects associated with the given subproject ID
     */
    public List<Task> readTasksBySubprojectId(int subprojectId) {
        return taskRepository.readTasksBySubprojectId(subprojectId);
    }

    /**
     * Retrieves a task by its ID.
     * @param taskId the ID of the task to retrieve, represented as a String
     * @return an Optional containing the Task if found, or an empty Optional if no task with the given ID exists
     */
    public Optional<Task> getTaskById(String taskId) {
        return Optional.ofNullable(taskRepository.getTaskById(Integer.parseInt(taskId)));
    }

    /**
     * Creates a new task by delegating to the task repository.
     *
     * @param task the task to be created, containing all necessary task details
     * @return the created task after successfully persisting it in the repository
     */
    public Task createTask(Task task) {
        taskRepository.createTask(task);
        return task;
    }

    /**
     * Marks a task as complete based on the provided task ID by updating its status in the repository.
     * @param taskId the ID of the task to be marked as complete
     */
    public void markComplete(int taskId) {
        taskRepository.markComplete(taskId);
    }


    /**
     * Updates an existing task with the provided updated details.
     * @param taskId the ID of the task to be updated
     * @param updatedTask the updated Task object containing the new task information
     * @throws IllegalArgumentException if the task with the given ID does not exist
     */
    public void updateTask(int taskId, Task updatedTask) {
        Optional<Task> existingTask = getTaskById(String.valueOf(taskId));

        if (existingTask.isPresent()) {
            taskRepository.updateTask(
                    updatedTask.getTaskName(),
                    taskId,
                    updatedTask.getAssignedEmployees(),
                    (int) updatedTask.getEstimatedCost(),
                    updatedTask.getStartDate(),
                    updatedTask.getEndDate(),
                    updatedTask.getIsComplete(),
                    updatedTask.getTaskDescription()
            );
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " does not exist.");
        }
    }

    /**
     * Deletes a task from the repository based on the provided task ID.
     * @param taskId the unique identifier of the task to be deleted
     */
    public void deleteTask(int taskId) {
        taskRepository.deleteTaskById(Integer.parseInt(String.valueOf(taskId)));
    }
}
