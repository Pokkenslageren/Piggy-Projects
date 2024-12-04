package ProjectPortal.Model;

public class User {
    private int companyId;
    private String username;
    private String password;
    private int userId;

    public User(int companyId, String username, String password, int userId) {
        this.companyId = companyId;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public User() {}

    public int getCompanyId() {
        return companyId;
    }
    public String getUserName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getUserId() {
        return userId;
    }
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public void setUserName(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
