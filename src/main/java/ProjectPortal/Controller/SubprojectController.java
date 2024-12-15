package ProjectPortal.Controller;

import ProjectPortal.Model.Project;

import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.SubprojectService;
import ProjectPortal.Service.UserService;
import ProjectPortal.Service.ProjectService;
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
            subproject.setParentProjectID(projectId);
            subproject.setComplete(false);

            model.addAttribute("subproject", subproject);
            model.addAttribute("project" , project);

            return "create-subproject";
        }

        @PostMapping("/{userId}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Subproject subproject){
            subproject.setParentProjectID(projectId);
            subprojectService.createSubproject(subproject);
            return "redirect:/{userId}/portfolio/{projectId}";
        }

    }

