package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.ProjectService;
import ProjectPortal.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import ProjectPortal.Service.SubprojectService;

@Controller
@RequestMapping("")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final SubprojectService subprojectService;
    public ProjectController(ProjectService projectService, UserService userService, SubprojectService subprojectService) {
        this.projectService = projectService;
        this.userService = userService;
        this.subprojectService = subprojectService;
    }

    @GetMapping("/{userId}/portfolio/createproject")
    public String createProject(@PathVariable("userId") int userId, Model model){
        User user = userService.readUserById(userId);
        Project project = new Project();
        project.setUserId(userId);
        project.setCompanyId(user.getCompanyId());
        project.setComplete(false);
        model.addAttribute("project", project);
        return "create-project";
    }

    @GetMapping("/{userId}/portfolio")
    public String showPortfolio(@PathVariable("userId") int userId, Model model){
        User user = userService.readUserById(userId);
        List<Project> projects = projectService.readAllProjects();
        model.addAttribute("projects", projects);
        model.addAttribute("user", user);
        return "portfolio";
    }


    @GetMapping("/{userId}/portfolio/{projectId}")
    public String showProject(@PathVariable int userId, @PathVariable int projectId, Model model) {
        Project project = projectService.readProject(projectId);
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);
        model.addAttribute("project", project);
        model.addAttribute("subprojects", subprojects);
        return "project-overview";
    }

    @PostMapping("/{userId}/portfolio/createproject")
    public String createProject(@PathVariable("userId") int userId, @ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/portfolio";
    }

    @GetMapping("/{userId}/portfolio/{projectid}/update")
    public String updateProject(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId, Model model){
        Project project = projectService.readProject(projectId);
        model.addAttribute("project", project);
        return "update-project";
    }

    @PostMapping("/{userId}/portfolio/{projectid}/update")
    public String updateProject(@PathVariable("userId") int userId, @PathVariable("projectid")int projectid, @ModelAttribute Project project){
        projectService.updateProject(project, projectid);
        return "redirect:/portfolio";
    }




    @GetMapping("/{userId}/portfolio/{projectid}/delete")
    public String deleteProject(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId){

        projectService.deleteProject(projectId);
        return "redirect:/portfolio";
    }
}
