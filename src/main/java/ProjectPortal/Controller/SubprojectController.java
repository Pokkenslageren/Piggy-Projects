package ProjectPortal.Controller;

import ProjectPortal.Model.Project;

import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.SubprojectService;
import ProjectPortal.Service.UserService;
import ProjectPortal.Service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SubprojectController {

        private final SubprojectService subprojectService;
        private final UserService userService;
        private final ProjectService projectService;

        public SubprojectController(SubprojectService subprojectService, UserService userService, ProjectService projectService) {
            this.subprojectService = subprojectService;
            this.userService = userService;
            this.projectService = projectService;
        }

        //todo fix user
        @GetMapping("/{userId}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, Model model){
            User user = userService.readUserById(userId);
            Project project = projectService.readProject(projectId);
            Subproject subproject = new Subproject();
            subproject.setProjectId(projectId);
            subproject.setComplete(false);
            subproject.setTotalAssignedEmployees(0);

            model.addAttribute("subproject", subproject);
            model.addAttribute("project" , project);

            return "create-subproject";
        }

        @PostMapping("/{userId}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Subproject subproject){
            subproject.setProjectId(projectId);
            subproject.setTotalAssignedEmployees(0);
            subprojectService.createSubproject(subproject);
            return "redirect:/" + userId + "/portfolio/" + projectId;
        }

    @PostMapping("/subproject/{subprojectId}/complete")
    @ResponseBody
    public ResponseEntity<String> markSubprojectComplete(@PathVariable int subprojectId) {
        try {
            subprojectService.markComplete(subprojectId);
            return ResponseEntity.ok("Subproject marked complete");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking subproject complete");
        }
    }

    @GetMapping("/{userId}/portfolio/{projectId}/{subprojectId}/deletesubproject")
    public String deleteSubproject(@PathVariable("userId") int userId,
                                   @PathVariable("projectId") int projectId,
                                   @PathVariable("subprojectId") int subprojectId) {
        subprojectService.deleteSubproject(subprojectId);
        return "redirect:/" + userId + "/portfolio/" + projectId;
    }

    }

