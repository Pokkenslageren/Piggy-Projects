package ProjectPlanner.Controller;

import ProjectPlanner.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ProjectController {

    private final ProjectService projectService();
    public ProjectController() {

    }
    
}
