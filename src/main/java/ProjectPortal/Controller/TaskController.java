package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Task;
import ProjectPortal.Model.User;
import ProjectPortal.Service.ProjectService;
import ProjectPortal.Service.TaskService;
import ProjectPortal.Service.UserService;
import ProjectPortal.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
        this.taskService = taskService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/{userId}/portfolio/{projectId}/createtask")
    public String createTask(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, Model model) {
        User user = userService.readUserById(userId);
        Project project = projectService.readProject(projectId);  // Get project info
        Task task = new Task();
        task.setProjectId(projectId);
        task.setIsComplete(false);

        model.addAttribute("task", task);
        model.addAttribute("project", project);  // Need project for navigation
        return "create-task";
    }

    @PostMapping("/{userId}/portfolio/{projectId}/createtask")
    public String createTask(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/" + userId + "/portfolio/" + projectId;  // Redirect back to project view
    }

}
