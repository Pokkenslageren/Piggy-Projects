package ProjectPortal.Controller;

import ProjectPortal.Service.UserService;
import ProjectPortal.Model.User;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get mapping that creates a user and then receive data from the server
     * @param model
     * @return
     */
    @GetMapping("/{userId}/portfolio/createUser")
    public String createUser(@PathVariable("userId") Model model) {
        User user = new User();
           if(user == null) {
                model.addAttribute("Error", "user not found");
            return "redirect:/error";
            }
        model.addAttribute("user", user);
        return "create-user";
    }

    /**
     * PostMapping send the data to server when a user is created
     * @param user
     * @return
     */
    @PostMapping("/{userId}/portfolio/createUser")
    public String createUser(@PathVariable("userId") @ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/portfolio";
    }

    /**
     * Show all users by receive data from the server
     * @param user
     * @return
     */
    @GetMapping
    public String readAllUsers(User user) {
        userService.readUsers(user.getUserName(), user.getCompanyId(), user.getUserId());
        return "redirect:/home";
    }

    /**
     * Updates the user by using userId
     * @param user
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/portfolio/update")
    public String updateUser(@PathVariable("userId") User user, int userId) {
        userService.updateUser(user, userId);
        return "update-user";
    }

    /**
     * Send the data from the updated user to the server
     * @param userId
     * @param user
     * @return
     */
    @PostMapping("/{userId}/portfolio/update")
    public String updateUser(@PathVariable("userId")int userId,  @ModelAttribute User user) {
        userService.updateUser(user,userId);
        return "redirect:/home";
    }

    /**
     * Delete a user by userID
     * @param userId
     * @return
     */
    @GetMapping("/{userId}/portfolio/delete")
    public String deleteUser(@PathVariable("userId")int userId) {
        userService.deleteUser(userId);
        return "redirect:/home";
    }
}