package ProjectPortal.Repository;

import ProjectPortal.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        //
    }

    public User authenticate(String username, String password) {
        String query = "SELECT user_id as userId, company_id as companyId, user_name as userName, user_password as password FROM users WHERE user_name = ?";
        try {
            User user = jdbcTemplate.queryForObject(query,
                    new BeanPropertyRowMapper<>(User.class), username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (DataAccessException e) {
            return null;
        }
        return null;
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
        try {
            String query = "SELECT * FROM users WHERE user_id = ?";
            RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
            return jdbcTemplate.queryForObject(query, rowMapper, userId);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Create a user
     */
    public void createUser(User user) {
        try {
            String query = "INSERT INTO users (user_name, user_password, company_Id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getCompanyId());
        } catch (DataAccessException e) {
            throw new RuntimeException("Cannot create user", e);
        }
    }

    /**
     * Update a users info
     * @param userId
     */
    public void updateUser(User user, int userId) {

        try {
            String query = "UPDATE Users " + "SET user_name = ?, " + "user_password = ?, " + "company_Id = ?, " + "WHERE id = ?";
            jdbcTemplate.update(query, userId);
        } catch (DataAccessException e) {
            throw new RuntimeException("Could not update user", e);
        }
    }


    /**
     * Delete a user
     * @param userId
     */
    public void deleteUser(int userId) {
      try {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, userId);
    } catch (DataAccessException e) {
        throw new RuntimeException("Could not delete user", e);
    }
    }
}
