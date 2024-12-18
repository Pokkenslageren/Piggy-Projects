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

        /**
         * Handles the creation of a subproject for a specific user and project by adding necessary attributes
         * to the model and forwarding to the subproject creation page.
         * @param userId the ID of the user associated with the project
         * @param projectId the ID of the project under which the subproject is being created
         * @param model the model to add attributes required for rendering the subproject creation view
         * @return the name of the view responsible for rendering the subproject creation form
         */
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

        /**
         * Handles the creation of a new subproject under a specific project.
         * Sets the project ID for the subproject and initializes its total assigned employees to 0.
         * Redirects to the portfolio page of the user after successfully creating the subproject.
         * @param userId the ID of the user to whom the portfolio belongs
         * @param projectId the ID of the project under which the subproject is created
         * @param subproject the subproject details submitted through the form
         * @return the redirection path to the user's portfolio for the specific project
         */
        @PostMapping("/{userId}/portfolio/{projectId}/createsubproject")
        public String createProject(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId, @ModelAttribute Subproject subproject){
            Project project = projectService.readProject(projectId);
            subproject.setProjectId(projectId);
            subproject.setTotalAssignedEmployees(0);
            project.setTotalEstimatedCost(project.getTotalEstimatedCost() + subproject.getTotalEstimatedCost());
            projectService.updateProject(project, projectId);
            subprojectService.createSubproject(subproject);
            return "redirect:/" + userId + "/portfolio/" + projectId;
        }

    /**
     * Marks the specified subproject as complete.
     * @param subprojectId the unique identifier of the subproject to be marked as complete
     * @return a ResponseEntity containing a success message if the operation is successful,
     *         or an error message with HTTP status INTERNAL_SERVER_ERROR in case of failure
     */
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

    /**
     * Deletes a subproject associated with a specific user and project.
     * @param userId       the unique identifier of the user
     * @param projectId    the unique identifier of the project to which the subproject belongs
     * @param subprojectId the unique identifier of the subproject to be deleted
     * @return a redirection URL to the user's portfolio page for the given project
     */
    @GetMapping("/{userId}/portfolio/{projectId}/{subprojectId}/deletesubproject")
    public String deleteSubproject(@PathVariable("userId") int userId,
                                   @PathVariable("projectId") int projectId,
                                   @PathVariable("subprojectId") int subprojectId) {
        subprojectService.deleteSubproject(subprojectId);
        return "redirect:/" + userId + "/portfolio/" + projectId;
    }

    }

