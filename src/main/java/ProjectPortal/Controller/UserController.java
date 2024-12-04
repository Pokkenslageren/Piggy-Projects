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
        userService.createUser(user.getUserName(),user.getPassword(), user.getUserId(),user.getCompanyId());
        return "redirect:/home";
    }

    @GetMapping("/{user}/home/{user}/update")
    public String updateUser(@PathVariable("user")String username, String password, int companyId, int userId, @ModelAttribute User user) {
        userService.updateUser(username, password, companyId, userId);
        return "update-user";
    }

    @PostMapping("/{user}/home/{user}/update")
    public String updateUser(@ModelAttribute("user") String username, String password, int companyId, int userId) {
        userService.updateUser(username, password, companyId, userId);
        return "redirect:/home";
    }

    @GetMapping("/{user}/home/{user}/delete")
    public String deleteUser(@PathVariable("user")int userId) {
        userService.deleteUser(userId);
        return "redirect:/home";
    }
}