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
        model.addAttribute("subprojects", subprojects);  // Add this line
        return "create-task";
    }

    @PostMapping("/{userId}/portfolio/{projectId}/createtask")
    public String createTask(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Task task) {

        Subproject subproject = subprojectService.readSubproject(task.getSubprojectId());

        if (task.getAssignedEmployees() > subproject.getTotalAssignedEmployees()) {

            task.setAssignedEmployees(subproject.getTotalAssignedEmployees());
        }

        taskService.createTask(task);
        return "redirect:/" + userId + "/portfolio/" + projectId;
    }

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

}
