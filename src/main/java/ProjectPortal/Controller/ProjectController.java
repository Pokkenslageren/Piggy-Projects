package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Task;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.ProjectService;
import ProjectPortal.Service.TaskService;
import ProjectPortal.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import ProjectPortal.Service.SubprojectService;

@Controller
@RequestMapping("")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final SubprojectService subprojectService;
    private final TaskService taskService;
    public ProjectController(ProjectService projectService, UserService userService, SubprojectService subprojectService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.subprojectService = subprojectService;
        this.taskService = taskService;
    }

    /**
     * Creates a project Object using create-project.html
     * @param userId ID of the user
     * @param model Object used by springframework
     * @return A String reference to create-project.html
     */
    @GetMapping("/{userId}/portfolio/createproject")
    public String createProject(@PathVariable("userId") int userId, Model model){
        User user = userService.readUserById(userId);
        Project project = new Project();
        project.setUserId(userId);
        project.setCompanyId(user.getCompanyId());
        project.setComplete(false);
        model.addAttribute("project", project);
        model.addAttribute("user", user);
        return "create-project";
    }

    @GetMapping("/{userId}/portfolio")
    public String showPortfolio(@PathVariable("userId") int userId, Model model) {
        User user = userService.readUserById(userId);
        List<Project> projects = projectService.readAllProjects();

        // Calculate totals for each project
        for (Project project : projects) {
            List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(project.getProjectId());

            int totalProjectEmployees = 0;
            int totalProjectHours = 0;
            double totalProjectCost = 0;

            for (Subproject subproject : subprojects) {
                List<Task> tasks = taskService.readTasksBySubprojectId(subproject.getSubprojectId());

                int subprojectEmployees = 0;
                double subprojectCost = 0;
                int subprojectHours = 0;

                for (Task task : tasks) {
                    subprojectEmployees += task.getAssignedEmployees();
                    subprojectCost += task.getEstimatedCost();
                    subprojectHours += task.getHoursAllocated();
                }

                subproject.setTotalAssignedEmployees(subprojectEmployees);
                subproject.setTotalEstimatedCost(subprojectCost);
                subproject.setHoursAllocated(subprojectHours);

                totalProjectEmployees += subprojectEmployees;
                totalProjectCost += subprojectCost;
                totalProjectHours += subprojectHours;
            }

            project.setTotalAssignedEmployees(totalProjectEmployees);
            project.setTotalEstimatedCost(totalProjectCost);
        }

        model.addAttribute("projects", projects);
        model.addAttribute("user", user);
        return "portfolio";
    }


    /**
     * Displays the details of a specific project and its associated subprojects and tasks.
     * Retrieves information about the user, project, subprojects, and tasks,
     * and updates the model with this data for rendering the project overview page.
     * @param userId the ID of the user who owns the project
     * @param projectId the ID of the project to display
     * @param model the model object used to pass attributes to the view
     * @return the name of the view that displays the project overview
     */
    @GetMapping("/{userId}/portfolio/{projectId}")
    public String showProject(@PathVariable int userId, @PathVariable int projectId, Model model) {
        User user = userService.readUserById(userId);
        Project project = projectService.readProject(projectId);
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);

        for (Subproject subproject : subprojects) {
            List<Task> tasks = taskService.readTasksBySubprojectId(subproject.getSubprojectId());

            int totalEmployees = 0;
            double totalCost = 0;
            int totalHours = 0;

            for (Task task : tasks) {
                totalEmployees += task.getAssignedEmployees();
                totalCost += task.getEstimatedCost();
                totalHours += task.getHoursAllocated();
            }

            subproject.setTotalAssignedEmployees(totalEmployees);
            subproject.setTotalEstimatedCost(totalCost);
            subproject.setHoursAllocated(totalHours);
            subproject.setTasks(tasks);
        }

        model.addAttribute("user", user);
        model.addAttribute("project", project);
        model.addAttribute("subprojects", subprojects);
        return "project-overview";
    }

    /**
     * Handles the creation of a new project for a specific user.
     * @param userId the ID of the user for whom the project is being created
     * @param project the project object containing the details of the new project
     * @return a redirect URL to the user's portfolio page
     */
    @PostMapping("/{userId}/portfolio/createproject")
    public String createProject(@PathVariable("userId") int userId, @ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/" + userId + "/portfolio";
    }

    /**
     * Handles the HTTP GET request for rendering the update project page.
     * This method retrieves the user details and project details based on the provided userId and projectId,
     * and adds them as attributes to the model for use in the view.
     * @param userId the ID of the user whose project is being updated
     * @param projectId the ID of the project to be updated
     * @param model the model object used to pass data to the view
     * @return the name of the view to be rendered for updating the project
     */
    @GetMapping("/{userId}/portfolio/{projectId}/update")
    public String updateProject(@PathVariable int userId, @PathVariable int projectId, Model model) {
        User user = userService.readUserById(userId);
        Project project = projectService.readProject(projectId);

        model.addAttribute("user", user);
        model.addAttribute("project", project);

        return "update-project";
    }

    /**
     * Updates the project with the given information for the specified user and project ID.
     * @param userId the ID of the user to whom the project belongs.
     * @param projectid the ID of the project to be updated.
     * @param project the project object containing updated project details.
     * @return a redirect URL to the updated portfolio page of the user.
     */
    @PostMapping("/{userId}/portfolio/{projectid}/update")
    public String updateProject(@PathVariable("userId") int userId, @PathVariable("projectid")int projectid, @ModelAttribute Project project){
        projectService.updateProject(project, projectid);
        return "redirect:/" + userId +"/portfolio";
    }


    /**
     * Deletes a project for a specified user and redirects to the user's portfolio view.
     * @param userId the ID of the user whose project is being deleted
     * @param projectId the ID of the project to be deleted
     * @return a redirect string to the user's portfolio view
     */
    @GetMapping("/{userId}/portfolio/{projectId}/deleteproject")
    public String deleteProject(@PathVariable("userId") int userId, @PathVariable("projectId") int projectId) {
        projectService.deleteProject(projectId);
        return "redirect:/" + userId + "/portfolio";
    }

    @PostMapping("/project/{projectId}/complete")
    @ResponseBody
    public ResponseEntity<String> markProjectComplete(@PathVariable int projectId) {
        try {
            projectService.markComplete(projectId);
            return ResponseEntity.ok("Project marked complete");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error marking project complete");
        }
    }



    /**
     * Displays detailed analytics for a specific project and its associated subprojects.
     * The analytics include data visualization for allocated hours, estimated costs,
     * actual costs, and timelines, which are displayed in various chart formats
     * (e.g., pie charts, histograms, and Gantt timelines).
     * @param userId The ID of the user who owns the portfolio.
     * @param projectId The ID of the project for which analytics are displayed.
     * @param model The model object used to pass data to the view layer.
     * @return The name of the view template to render the project analytics page.
     */
    @GetMapping("/{userId}/portfolio/{projectid}/analytics")
    public String displayAnalytics(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId, Model model){
        Project project = projectService.readProject(projectId);
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);
        List<List<Object>> subprojectData = new ArrayList<>();
        List<List<Object>> subprojectEstimatedCostPie = new ArrayList<>();
        List<List<Object>> subprojectGantt = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Subproject s : subprojects){
            subprojectData.add(List.of(s.getSubprojectName(),s.getHoursAllocated()));
        }

        for (Subproject s : subprojects){
            subprojectGantt.add(List.of("Subproject ID: " + s.getSubprojectId(), s.getSubprojectName(), s.getStartDate().format(dateTimeFormatter),s.getEndDate().format(dateTimeFormatter)));
        }

        for(Subproject s : subprojects){
            subprojectEstimatedCostPie.add(List.of(s.getSubprojectName(), s.getTotalEstimatedCost()));
        }


        model.addAttribute("project", project);
        model.addAttribute("subprojectData", subprojectData );
        model.addAttribute("subprojectGantt",subprojectGantt);
        model.addAttribute("subprojectEstimatedCostPie", subprojectEstimatedCostPie);
        return "project-analytics";
    }

    /**
     * Displays the analytics for a specific subproject by fetching task-related data
     * and charts, and adding these details to the model for rendering in the view.
     * @param userId the ID of the user owning the portfolio
     * @param projectId the ID of the parent project containing the subproject
     * @param subprojectId the ID of the subproject to fetch analytics for
     * @param model the model object to store data and attributes for the view
     * @return the name of the view template to render the subproject analytics page
     */
    @GetMapping("/{userId}/portfolio/{projectid}/{subprojectid}/analytics")
    public String displayAnalyticsSubproject(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId, @PathVariable("subprojectid") int subprojectId, Model model){
        Project project = projectService.readProject(projectId);
        Subproject subproject = subprojectService.readSubproject(subprojectId);
        List<List<Object>> taskData = new ArrayList<>();
        List<List<Object>> taskEstimatedCostPie = new ArrayList<>();
        List<List<Object>> ganttChartTasks = new ArrayList<>();
        List<Task> tasks = subprojectService.readAllTasksBySubproject(subprojectId);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Task t : tasks){
            taskData.add(List.of(t.getTaskName(),t.getHoursAllocated()));
        }

        for(Task t : tasks){
            taskEstimatedCostPie.add(List.of(t.getTaskName(), t.getEstimatedCost()));
        }


        for(Task t : tasks){
            ganttChartTasks.add(List.of("Task ID: " + t.getTaskId(), t.getTaskName(), t.getStartDate().format(dateTimeFormatter), t.getEndDate().format(dateTimeFormatter)));
        }

        model.addAttribute("project", project);
        model.addAttribute("taskData", taskData);
        model.addAttribute("taskEstimatedCostPie", taskEstimatedCostPie);
        model.addAttribute("ganttChartTasks", ganttChartTasks);

        return "subproject-analytics";
    }


}
