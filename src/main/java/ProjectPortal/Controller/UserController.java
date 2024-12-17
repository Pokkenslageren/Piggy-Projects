package ProjectPortal.Controller;

import ProjectPortal.Service.UserService;
import ProjectPortal.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String runLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String runLogin(@RequestParam String username,
                           @RequestParam String password,
                           HttpSession session) {
        User user = userService.authenticate(username, password);
        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            return "redirect:/" + user.getUserId() + "/portfolio";
        }
        return "redirect:/login?error";
    }

    /**
     * Get mapping
     * @param model
     * @return
     */
    @GetMapping("/{userId}/portfolio/createUser")
    public String createUser(@PathVariable("userId") Model model) {
        User user = new User();
//            if(user == null) {
//                model.addAttribute("Error", "user not found");
//            return "redirect:/error";
//            }
        model.addAttribute("user", user);
        return "create-user";
    }

    @PostMapping("/{userId}/portfolio/createUser")
    public String createUser(@PathVariable("userId") @ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/portfolio";
    }

    @GetMapping
    public String readAllUsers(User user) {
        userService.readUsers(user.getUserName(), user.getCompanyId(), user.getUserId());
        return "redirect:/home";
    }


    @GetMapping("/{userId}/portfolio/delete")
    public String deleteUser(@PathVariable("userId")int userId) {
        userService.deleteUser(userId);
        return "redirect:/home";
    }
}