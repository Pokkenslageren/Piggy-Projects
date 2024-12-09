package ProjectPortal.Controller;

import ProjectPortal.Service.UserService;
import ProjectPortal.Model.User;
import org.springframework.ui.Model;
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
     * @param model
     * @return
     */
    @GetMapping("/{user}/portfolio/createUser")
    public String createUser(@PathVariable("user") Model model) {
        User user = new User();
//            if(user == null) {
//                model.addAttribute("Error", "user not found");
//            return "redirect:/error";
//            }
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/{user}/portfolio/createUser")
    public String createUser(@PathVariable("user") @ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/portfolio";
    }

    @GetMapping
    public String readAllUsers(User user) {
        userService.readUsers(user.getUserName(), user.getCompanyId(), user.getUserId());
        return "redirect:/home";
    }

    @GetMapping("/{user}/portfolio/update")
    public String updateUser(@PathVariable("user") User user, int userId) {
        userService.updateUser(user, userId);
        return "update-user";
    }

    @PostMapping("/{user}/portfolio/update")
    public String updateUser(@PathVariable("user")int userId,  @ModelAttribute User user) {
        userService.updateUser(user,userId);
        return "redirect:/home";
    }

    @GetMapping("/{user}/portfolio/delete")
    public String deleteUser(@PathVariable("user")int userId) {
        userService.deleteUser(userId);
        return "redirect:/home";
    }
}