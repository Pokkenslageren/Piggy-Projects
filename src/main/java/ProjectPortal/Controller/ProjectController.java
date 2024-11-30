package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Service.ProjectService;
import ProjectPortal.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/{user}/home/createproject")
    public String createProject(@PathVariable("user") int userId, Model model){
        userService.readUserById(userId);
        Project project = new Project();
        model.addAttribute("project", project);
        return "create-project";
    }

    @PostMapping("/{user}/home/createproject")
    public String createProject(@PathVariable("user") int userId, @ModelAttribute Project project){

        return "redirect:/home";
    }

}
