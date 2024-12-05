package ProjectPortal.Repository;

import ProjectPortal.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets all users
     * @return
     */
    public List<User> readUsers(String username, int userId, int companyId) {
        String query = "SELECT * FROM user";
        RowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(query, rowMapper, username, userId, companyId);
    }

    /**
     * Get user by id
     * @param userId
     * @return
     */
    public User readUserById(int userId){
        String query = "SELECT * FROM user WHERE id = ?";
        RowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
        return (User) jdbcTemplate.query(query, rowMapper, userId);
    }

    /**
     * Create a user
     */
    public void createUser(User user) {
        String query = "INSERT INTO users (user_name, user_password, company_Id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getCompanyId());
    }

    /**
     * Update a users info
     */
    public void updateUser(User user, int userId) {
        String query = "UPDATE User " +
                "SET user_name = ?, " +
                "user_password = ?, " +
                "company_Id = ?, " +
                "WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }

    /**
     * Delete a user
     * @param userId
     */
    public void deleteUser(int userId) {
        String query = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }
}
