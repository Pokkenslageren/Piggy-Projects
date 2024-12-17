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
     * Retrieves a list of users based on the provided query parameters.
     * @param username   the username of the user(s) to be retrieved
     * @param userId     the unique ID of the user(s) to be retrieved
     * @param companyId  the ID of the company to which the user(s) belong
     * @return a list of {@code User} objects that match the query parameters
     */
    public List<User> readUsers(String username, int userId, int companyId) {
        String query = "SELECT * FROM users";
        RowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(query, rowMapper, username, userId, companyId);
    }

    /**
     * Retrieves a User object from the database using the specified user ID.
     * @param userId the ID of the user to retrieve
     * @return the {@code User} object corresponding to the specified user ID
     *         or throws an exception if no user is found
     */
    public User readUserById(int userId){
        String query = "SELECT * FROM users WHERE user_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(query, rowMapper, userId);
    }

    /**
     * Inserts a new user into the database with the provided user details.
     * @param user the User object containing the details of the user to be added.
     *             This includes the username, password, and associated company ID.
     */
    public void createUser(User user) {
        String query = "INSERT INTO users (user_name, user_password, company_Id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getCompanyId());
    }

    /**
     * Updates an existing user's details in the database based on their unique identifier.
     * @param user The User object containing the new details to update. This includes fields such as:
     *             username, password, and company ID.
     * @param userId The unique identifier of the user to be updated.
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
     * Deletes a user from the database by their unique identifier.
     * @param userId the unique identifier of the user to be deleted
     */
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, userId);
    }
}
