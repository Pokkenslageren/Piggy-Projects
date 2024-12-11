package ProjectPortal.Controller;

import ProjectPortal.Model.Task;
import ProjectPortal.Model.User;
import ProjectPortal.Service.TaskService;
import ProjectPortal.Service.UserService;
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

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/portfolio/{projectId}/{subprojectId}/createtask")
    public String createTask(@PathVariable("userId") int userId,@PathVariable("projectId") int projectId, @PathVariable("subprojectId") int subprojectId, Model model) {
        User user = userService.readUserById(userId);
        Task task = new Task();
        task.setSubprojectId(subprojectId);
        model.addAttribute("task", task);
        return "create-task";
    }

    @PostMapping("/{userId}/portfolio/createtask")
    public String createTask(@PathVariable("userId") int userId, @ModelAttribute Task task){
        taskService.createTask(task);
        return "redirect:/portfolio";
    }

}
