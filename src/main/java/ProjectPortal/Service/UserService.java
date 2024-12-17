package ProjectPortal.Service;

import ProjectPortal.Model.User;
import ProjectPortal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    private UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User authenticate(String username, String password) {
        return userRepository.authenticate(username, password);
    }

    public List<User> readUsers(String username, int companyId, int userId) {
        return userRepository.readUsers(username, userId, companyId);
    }

    public User readUserById(int userId) {
        return userRepository.readUserById(userId);
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }
    public void updateUser(User user, int userId) {
        userRepository.updateUser(user, userId);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }
}