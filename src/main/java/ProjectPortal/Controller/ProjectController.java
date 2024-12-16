package ProjectPortal.Controller;

import ProjectPortal.Model.Project;
import ProjectPortal.Model.Task;
import ProjectPortal.Model.Subproject;
import ProjectPortal.Model.User;
import ProjectPortal.Service.ProjectService;
import ProjectPortal.Service.TaskService;
import ProjectPortal.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

        for (Project project : projects) {
            List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(project.getProjectId());
            double calculatedActualCost = projectService.calculateTotalActualCost(subprojects);
            System.out.println("Project: " + project.getProjectName() +
                    " Actual Cost: " + calculatedActualCost);  // Debug print
            project.setTotalActualCost(calculatedActualCost);
        }

        model.addAttribute("projects", projects);
        model.addAttribute("user", user);
        return "portfolio";
    }

    @GetMapping("/{userId}/portfolio/{projectId}")
    public String showProject(@PathVariable int userId, @PathVariable int projectId, Model model) {
        Project project = projectService.readProject(projectId);

        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);

        for (Subproject subproject : subprojects) {
            List<Task> tasks = taskService.readTasksBySubprojectId(subproject.getSubprojectId());
            subproject.setTasks(tasks);
            double subprojectActualCost = tasks.stream()
                    .mapToDouble(Task::getEstimatedCost)
                    .sum();
            subproject.setTotalActualCost(subprojectActualCost);

        }

        double calculatedActualCost = projectService.calculateTotalActualCost(subprojects);
        project.setTotalActualCost(calculatedActualCost);

        model.addAttribute("project", project);
        model.addAttribute("subprojects", subprojects);
        return "project-overview";
    }


    @PostMapping("/{userId}/portfolio/createproject")
    public String createProject(@PathVariable("userId") int userId, @ModelAttribute Project project){
        projectService.createProject(project);
        return "redirect:/" + userId + "/portfolio";
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
        return "redirect:/" + userId +"/portfolio";
    }


    @GetMapping("/{userId}/portfolio/{projectid}/delete")
    public String deleteProject(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId){
        projectService.deleteProject(projectId);
        return "redirect:/" + userId + "/portfolio";
    }

    @GetMapping("/{userId}/portfolio/{projectid}/analytics")
    public String displayAnalytics(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId, Model model){
        Project project = projectService.readProject(projectId);
        List<Subproject> subprojects = subprojectService.readAllSubprojectsByProjectId(projectId);
        List<List<Object>> subprojectData = new ArrayList<>();
        List<List<Object>> subprojectEstimatedCostPie = new ArrayList<>();
        List<List<Object>> costHistogram = new ArrayList<>();
        //Pie chart allocated hours
        for (Subproject s : subprojects){
            subprojectData.add(List.of(s.getSubprojectName(),s.getHoursAllocated()));
        }
        subprojectData.add(List.of("test",300));
        // timeline chart (gantt)
        List<List<Object>> subprojectGantt = new ArrayList<>();
        LocalDate startDate = LocalDate.of(2024,10,10);
        LocalDate endDate = LocalDate.of(2025,10,10);
        subprojectGantt.add(List.of("Eat","Me",projectService.formatForJavaScript(startDate),projectService.formatForJavaScript(endDate)));
        //Pie estimated cost
        for(Subproject s : subprojects){

            subprojectEstimatedCostPie.add(List.of(s.getSubprojectName(), s.getTotalEstimatedCost()));
        }
        costHistogram.add(List.of("Estimated Project Cost",project.getTotalEstimatedCost()));
        costHistogram.add(List.of("Actual Project Cost", projectService.calculateTotalActualCost(subprojects)));

        model.addAttribute("project", project);
        model.addAttribute("subprojectData", subprojectData );
        model.addAttribute("subprojectGantt",subprojectGantt);
        model.addAttribute("subprojectEstimatedCostPie", subprojectEstimatedCostPie);
        model.addAttribute("costHistogram", costHistogram);
        return "project-analytics";
    }

    @GetMapping("/{userId}/portfolio/{projectid}/{subprojectid}/analytics")
    public String displayAnalyticsSubproject(@PathVariable("userId") int userId, @PathVariable("projectid") int projectId, @PathVariable("subprojectid") int subprojectId, Model model){
        Subproject subproject = subprojectService.readSubproject(subprojectId);
        List<List<Object>> taskData = new ArrayList<>();
        List<List<Object>> taskEstimatedCostPie = new ArrayList<>();
        List<List<Object>> costBarChart = new ArrayList<>();
        List<Task> tasks = subprojectService.readAllTasksBySubproject(subprojectId);
        for (Task t : tasks){
            taskData.add(List.of(t.getTaskName(),t.getHoursAllocated()));
        }

        for(Task t : tasks){
            taskEstimatedCostPie.add(List.of(t.getTaskName(), t.getEstimatedCost()));
        }

        costBarChart.add(List.of("Estimated Subproject Cost", subproject.getTotalEstimatedCost()));
        costBarChart.add(List.of("Actual Subproject Cost", subprojectService.calculateTotalActualCost(tasks)));


/*        List<List<Object>> taskData = List.of(
                                                List.of("task1", 500),
                                                List.of("task2", 750),
                                                List.of("task3", 300)); */

        model.addAttribute("taskData", taskData);
        model.addAttribute("taskEstimatedCostPie", taskEstimatedCostPie);
        model.addAttribute("costBarChart", costBarChart);

        return "subproject-analytics";
    }
}
