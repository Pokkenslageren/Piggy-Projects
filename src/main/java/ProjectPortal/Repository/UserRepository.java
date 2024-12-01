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
    public List<User> readUsers() { //Er i tvivl om der skal være String userName i ()
        String query = "SELECT * FROM user";
        RowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(query, rowMapper);
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
     * @param username
     * @param password
     * @param companyId
     * @param userId
     */
    public void createUser(String username, String password, int companyId, int userId) {
        String query = "INSERT INTO users (user_name, user_password, company_Id, user_Id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, username, password, companyId, userId);
    }

    /**
     * Update a users info
     * @param username
     * @param password
     * @param companyId
     * @param userId
     */
    public void updateUser(String username, String password, int companyId, int userId) {
        String query = "UPDATE User " +
                "SET id = ?," +
                "user_name = ?, " +
                "user_password = ?, " +
                "companyId = ?, " +
                "user_Id = ?, " +
                "WHERE id = ?";
        jdbcTemplate.update(query, username, password, companyId, userId);
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