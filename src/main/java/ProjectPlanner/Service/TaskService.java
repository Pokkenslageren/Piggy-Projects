package ProjectPlanner.Service;

import ProjectPlanner.Model.Task;
import ProjectPlanner.Repository.TaskRepository;
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
     * Find all tasks
     * @return
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Find task by ID
     * @param taskId
     * @return
     */
    public Optional<Task> getTaskById(String taskId) {
        return taskRepository.findById(taskId);
    }

    /**
     * Save a new task
     * @param task
     * @return
     */
    public Task createTask(Task task) {
        return taskRepository.saveTask(task);
    }

    /**
     * Update existing Task
     * @param taskId
     * @param updatedTask
     * @return
     */
    public Task updateTask(String taskId, Task updatedTask) {
        // Find the task to update
        Optional<Task> existingTask = taskRepository.findById(taskId);

        if (existingTask.isPresent()) {
            // Update relevant fields
            Task task = existingTask.get();
            task.setTaskName(updatedTask.getTaskName());
            task.setAssignedEmployees(updatedTask.getAssignedEmployees());
            task.setEstimatedCost(updatedTask.getEstimatedCost());
            task.setStartDate(updatedTask.getStartDate());
            task.setEndDate(updatedTask.getEndDate());
            task.setIsComplete(updatedTask.getIsComplete());
            task.setTaskDescription(updatedTask.getTaskDescription());

            // Save updated task
            return taskRepository.update(task);
        } else {
            throw new IllegalArgumentException("Task with ID " + taskId + " does not exist.");
        }
    }

    /**
     * Delete a task by ID
     * @param taskId
     */
    public void deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
    }
}
