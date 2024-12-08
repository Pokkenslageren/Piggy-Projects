package ProjectPortal.Controller;

import ProjectPortal.Model.Project;

import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.SubprojectService;
import ProjectPortal.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class SubprojectController {

        private final SubprojectService subprojectService;
        private final UserService userService;

        public SubprojectController(SubprojectService subprojectService, UserService userService) {
            this.subprojectService = subprojectService;
            this.userService = userService;
        }

        //todo fix user
        @GetMapping("/{user}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("user") int userId, @PathVariable("projectId") int projectId, Model model){
            User user = userService.readUserById(userId);
            Subproject subproject = new Subproject();
            subproject.setParentProjectID(subproject.getParentProjectID());
            subproject.setComplete(false);
            model.addAttribute("subproject", subproject);
            return "create-subproject";
        }

        @PostMapping("/{user}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("user") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Subproject subproject){
            subprojectService.createSubproject(subproject);
            return "redirect:/{user}/portfolio/{projectId}";
        }

    }

