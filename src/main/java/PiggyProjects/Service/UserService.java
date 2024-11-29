package PiggyProjects.Service;


import PiggyProjects.Model.User;
import PiggyProjects.Repository.UserRepository;
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

    public List<User> readUsers(String username) {
        return userRepository.readUsers();
    }

    public User readUserById(int userId) {
        return userRepository.readUserById(userId);
    }

    public void createUser(String username, String password, int companyId, int userId) {
        userRepository.createUser(username, password, companyId, userId);
    }
    public void updateUser(String username, String password, int companyId, int userId) {
        userRepository.updateUser(username, password, companyId, userId);
    }

    public void deleteUser(int userId) {
        userRepository.deleteUser(userId);
    }


}
