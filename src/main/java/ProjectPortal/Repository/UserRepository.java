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
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Gets all users
     * @return
     */
    public List<User> readUsers(String username, int userId, int companyId) {
        String query = "SELECT * FROM users";
        RowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(query, rowMapper, username, userId, companyId);
    }

    /**
     * Get user by id
     * @param userId
     * @return
     */
    public User readUserById(int userId){
        String query = "SELECT * FROM users WHERE user_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(query, rowMapper, userId);
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
     * @param userId
     */
    public void updateUser(User user, int userId) {
        String query = "UPDATE Users " +
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
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }
}
