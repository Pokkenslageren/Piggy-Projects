package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.Task;
import ProjectPortal.Model.User;
import ProjectPortal.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;
    private final SubprojectService subprojectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService, SubprojectService subprojectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
        this.subprojectService = subprojectService;
    }

    /**
     * Handles the creation of a new task within a specified project for a specific user.
     * @param userId the ID of the user for whom the task is being created
     * @param projectId the ID of the project under which the task will be created
     * @param model the model object to supply attributes to the view
     * @return the name of the view to render the task creation page
     */
    @GetMapping("/{userId}/portfolio/{projectId}/createtask")
    public String createTask(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, Model model) {
        User user = userService.readUserById(userId);
        Project project = projectService.readProject(projectId);
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);

        Task task = new Task();
        task.setProjectId(projectId);
        task.setIsComplete(false);

        model.addAttribute("task", task);
        model.addAttribute("project", project);
        model.addAttribute("subprojects", subprojects);
        return "create-task";
    }

    /**
     * Creates a new task for the specified project and user. The assigned employees for the task will
     * be adjusted if the specified number exceeds the total employees assigned to the subproject.
     * @param userId the ID of the user to whom the portfolio belongs
     * @param projectId the ID of the project under which the task is created
     * @param task the task object containing the details of the new task
     * @return a string for redirecting to the portfolio page of the specified user and project
     */
    @PostMapping("/{userId}/portfolio/{projectId}/createtask")
    public String createTask(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Task task) {

        Subproject subproject = subprojectService.readSubproject(task.getSubprojectId());

        if (task.getAssignedEmployees() > subproject.getTotalAssignedEmployees()) {

            task.setAssignedEmployees(subproject.getTotalAssignedEmployees());
        }

        taskService.createTask(task);
        return "redirect:/" + userId + "/portfolio/" + projectId;
    }

    /**
     * Marks a task as complete based on the provided task ID.
     * @param taskId the ID of the task to be marked as complete
     * @return a ResponseEntity containing a success message if the task is marked complete,
     *         or an error message with an appropriate HTTP status code if marking fails
     */
    @PostMapping("/task/{taskId}/complete")
    @ResponseBody
    public ResponseEntity<String> markTaskComplete(@PathVariable int taskId) {
        try {
            taskService.markComplete(taskId);
            return ResponseEntity.ok("Task marked complete");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking task complete");
        }
    }

    /**
     * Deletes a task identified by its taskId from the specified project and user portfolio.
     * @param userId     the ID of the user who owns the portfolio
     * @param projectId  the ID of the project from which the task will be deleted
     * @param taskId     the ID of the task to be deleted
     * @return a redirection string to the user's portfolio page for the specified project
     */
    @GetMapping("/{userId}/portfolio/{projectId}/{taskId}/deletetask")
    public String deleteTask(@PathVariable("userId") int userId,
                             @PathVariable("projectId") int projectId,
                             @PathVariable("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/" + userId + "/portfolio/" + projectId;
    }

}
