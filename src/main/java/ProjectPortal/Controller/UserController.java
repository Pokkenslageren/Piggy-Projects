package ProjectPortal.Controller;

import ProjectPortal.Service.UserService;
import ProjectPortal.Model.User;
import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get mapping
     * @param userId
     * @param username
     * @param password
     * @param companyId
     * @param model
     * @return
     */
    @GetMapping("/{user}/home/createUser")
    public String createUser(@PathVariable("user")int userId, String username, String password, int companyId, Model model) {
        User user = userService.readUserById(userId);
//            if(user == null) {
//                model.addAttribute("Error", "user not found");
//            return "redirect:/error";
//            }
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/{user}/home/createUser")
    public String createUser(@PathVariable("user") int userId,@ModelAttribute User user) {
        userService.createUser(user.getUsername(),user.getPassword(), user.getUserId(),user.getCompanyId());
        return "redirect:/home";
    }

}